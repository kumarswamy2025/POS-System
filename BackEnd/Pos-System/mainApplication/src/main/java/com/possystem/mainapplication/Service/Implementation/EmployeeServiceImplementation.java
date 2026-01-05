package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.EmployeeService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.exceptions.EmployeException.EmployeeException;
import com.possystem.mainapplication.mapper.UserMapper;
import com.possystem.mainapplication.modal.BranchModal;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import com.possystem.mainapplication.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {
    //    here we inject repos
    private final UserRepo userRepo;
    private final StoreRepo storeRepo;
    private final BranchRepo branchRepo;


    //    here we inject services
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    //    create employee only  when role  is ROLE_STORE_ADMIN and ROLE_STORE_MANAGER
//    branch manager can also create employees
    @Override
    public UserDTO createStoreEmployee(UserDTO employee, Long storeId) {
//   checks if store is exists or not
        StoreModal storeModal = storeRepo.findById(storeId).orElseThrow(() -> new EmployeeException("store id is not present please check once...", HttpStatus.NOT_FOUND));

        BranchModal branchModal = null;

        if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
            if (employee.getBranchId() == null) {
                throw new EmployeeException("branch id is required to create branch manager", HttpStatus.BAD_REQUEST);
            }
            branchModal = branchRepo.findById(employee.getBranchId()).orElseThrow(() -> new EmployeeException("branch  not found..", HttpStatus.NOT_FOUND));

        }

        UserModal user = UserMapper.toEntity(employee);
        user.setStore(storeModal);
        user.setBranch(branchModal);
        user.setRole(employee.getRole());

        user.setPassword(passwordEncoder.encode(employee.getPassword()));

//        save user
        UserModal savedEmployee = userRepo.save(user);

//        update branch modal manager field if role is equal to ROLE_BRANCH_MANAGER
        if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branchModal != null) {
            branchModal.setManager(savedEmployee);
            branchRepo.save(branchModal);
        }


        return UserMapper.toDTO(savedEmployee);
    }

    //    create employee AND assigns a branch only  when role  is ROLE_BRANCH_MANAGER and ROLE_BRANCH_ADMIN
    @Override
    public UserDTO createBranchEmployee(UserDTO employee, Long branchId) {

        BranchModal branchModal = branchRepo.findById(branchId).orElseThrow(() -> new EmployeeException("branch not found...", HttpStatus.NOT_FOUND));

        if (employee.getRole() == UserRole.ROLE_BRANCH_CASHIER || employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
            UserModal userModal = UserMapper.toEntity(employee);
            userModal.setBranch(branchModal);
            userModal.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDTO(userRepo.save(userModal));
        }


        throw new EmployeeException("branch role not supported", HttpStatus.UNAUTHORIZED);
    }

    //    update an emoloyee when role is ROLE_BRANCH_MANAGER, ROLE_BRANCH_ADMIN, ROLE_STORE_MANAGER,ROLE_STORE_ADMIN
    @Override
    public UserModal updateEmployee(Long employeeId, UserDTO employeeDetails) {

//        checking employee is exists or not
        UserModal userModal = userRepo.findById(employeeId).orElseThrow(() -> new EmployeeException("employee not exists...", HttpStatus.NOT_FOUND));


// checking if name is not null then update it
        if (employeeDetails.getFullName() != null) {
            userModal.setFullName(employeeDetails.getFullName());
        }

        if (employeeDetails.getEmail() != null) {
            userModal.setEmail(employeeDetails.getEmail());
        }
        if (employeeDetails.getPhone() != null) {
            userModal.setPhone(employeeDetails.getPhone());
        }
        if (employeeDetails.getRole() != null) {
            userModal.setRole(employeeDetails.getRole());
        }


        if (employeeDetails.getPassword() != null) {
            userModal.setPassword(employeeDetails.getPassword());
        }
        if (employeeDetails.getUserStatus()!= null) {
            userModal.setUserStatus(employeeDetails.getUserStatus());
        }
        if (employeeDetails.getBranchId() != null) {
//            check if branch is exits or not
            BranchModal branchModal=branchRepo.findById(employeeDetails.getBranchId()).orElseThrow(
                    ()-> new EmployeeException("branch id is not found...",HttpStatus.NOT_FOUND)
            );
            userModal.setBranch(branchModal);
        }
        if (employeeDetails.getStoreId()!= null) {
//            check if store  is exits or not
            StoreModal storeModal=storeRepo.findById(employeeDetails.getStoreId()).orElseThrow(
                    ()-> new EmployeeException("branch id is not found...",HttpStatus.NOT_FOUND)
            );
            userModal.setStore(storeModal);
        }

        return userRepo.save(userModal);
    }

    //    delete employee when role is ROLE_STORE_ADMIN and ROLE_BRANCH_ADMIN
    @Override
    public void deleteEmployee(Long employeeId) {
//        checking if employee exists or not
        UserModal userModal=userRepo.findById(employeeId).orElseThrow(
                ()-> new EmployeeException("employee not found...",HttpStatus.NOT_FOUND)
        );
        userRepo.delete(userModal);

    }

    //  get store employees when role is  ROLE_STORE_MANAGER and ROLE_STORE_ADMIN
    @Override
    public List<UserModal> findStoreEmployees(Long storeId, UserRole userRole) {

        StoreModal storeModal=storeRepo.findById(storeId).orElseThrow(
                ()-> new EmployeeException("store is not found..",HttpStatus.NOT_FOUND)
        );

        return userRepo.findByStore(storeModal);


    }

    //  get branch employees when role is  ROLE_BRANCH_ADMIN and ROLE_BRANCH_MANAGER
    @Override
    public List<UserModal> findBranchEmployees(Long branchId, UserRole userRole) {
        BranchModal branchModal=branchRepo.findById(branchId).orElseThrow(
                ()-> new EmployeeException("branch is not found..",HttpStatus.NOT_FOUND)
        );

        return userRepo.findByBranchId(branchId);
    }

    //    get emoloyee details when role is ROLE_BRANCH_MANAGER, ROLE_BRANCH_ADMIN, ROLE_STORE_MANAGER,ROLE_STORE_ADMIN
    @Override
    public UserModal getEmployeeDetails(Long employeeId, UserRole userRole) {
        UserModal userModal=userRepo.findById(employeeId).orElseThrow(
                ()-> new EmployeeException("employee  is not found..",HttpStatus.NOT_FOUND)
        );

        return userModal;
    }
}
