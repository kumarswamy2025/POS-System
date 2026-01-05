package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.EmployeeService;
import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;

import java.util.List;

public class EmployeeServiceImplementation implements EmployeeService {
    @Override
    public UserDTO createEmployee(UserDTO userDTO, Long storeId) {
        return null;
    }

    @Override
    public UserDTO createBranchEmployee(UserDTO userDTO, Long branchId) {
        return null;
    }

    @Override
    public UserModal updateEmployee(Long employeeId, UserModal employeeDetails) {
        return null;
    }

    @Override
    public void deleteEmployee(Long employeeId) {

    }

    @Override
    public List<UserModal> findStoreEmployees(Long storeId, UserRole userRole) {
        return List.of();
    }

    @Override
    public List<UserModal> findBranchEmployees(Long branchId, UserRole userRole) {
        return List.of();
    }

    @Override
    public UserModal getEmployeeDetails(Long employeeId, UserRole userRole) {
        return null;
    }
}
