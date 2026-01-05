package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.EmployeeService;
import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;

import java.util.List;

public class EmployeeServiceImplementation implements EmployeeService {
//    create employee only  when role  is ROLE_STORE_ADMIN and ROLE_STORE_MANAGER
    @Override
    public UserDTO createEmployee(UserDTO userDTO, Long storeId) {
        return null;
    }
    //    create employee AND assigns a branch only  when role  is ROLE_BRANCH_MANAGER and ROLE_BRANCH_ADMIN
    @Override
    public UserDTO createBranchEmployee(UserDTO userDTO, Long branchId) {
        return null;
    }

//    update an emoloyee when role is ROLE_BRANCH_MANAGER, ROLE_BRANCH_ADMIN, ROLE_STORE_MANAGER,ROLE_STORE_ADMIN
    @Override
    public UserModal updateEmployee(Long employeeId, UserModal employeeDetails) {

//        add logic for update user role

        return null;
    }

//    delete employee when role is ROLE_STORE_ADMIN and ROLE_BRANCH_ADMIN
    @Override
    public void deleteEmployee(Long employeeId) {

    }
//  get store employees when role is  ROLE_STORE_MANAGER and ROLE_STORE_ADMIN
    @Override
    public List<UserModal> findStoreEmployees(Long storeId, UserRole userRole) {
        return List.of();
    }
    //  get branch employees when role is  ROLE_BRANCH_ADMIN and ROLE_BRANCH_MANAGER
    @Override
    public List<UserModal> findBranchEmployees(Long branchId, UserRole userRole) {
        return List.of();
    }

    //    get emoloyee details when role is ROLE_BRANCH_MANAGER, ROLE_BRANCH_ADMIN, ROLE_STORE_MANAGER,ROLE_STORE_ADMIN
    @Override
    public UserModal getEmployeeDetails(Long employeeId, UserRole userRole) {
        return null;
    }
}
