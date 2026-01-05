package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;

import java.util.List;

public interface EmployeeService {

    UserDTO createEmployee(UserDTO userDTO,Long storeId);
    UserDTO createBranchEmployee(UserDTO userDTO,Long branchId);
    UserModal updateEmployee(Long employeeId,UserModal employeeDetails);
    void  deleteEmployee(Long employeeId);
    List<UserModal> findStoreEmployees(Long storeId, UserRole userRole);
    List<UserModal> findBranchEmployees(Long branchId,UserRole userRole);
    UserModal getEmployeeDetails(Long employeeId,UserRole userRole);


}
