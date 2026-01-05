package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.EmployeeService;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create/storeemployee")
    public ResponseEntity<UserDTO> createStoreEmployee(@RequestBody  UserDTO userDTO){
        return ResponseEntity.ok(employeeService.createStoreEmployee(userDTO, userDTO.getStoreId()));
    }
    @PostMapping("/create/branchemployee")
    public ResponseEntity<UserDTO> createBranchEmployee(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(employeeService.createBranchEmployee(userDTO,userDTO.getBranchId()));
    }

    @PutMapping("/updateemployee/{id}")
    public ResponseEntity<UserModal> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId,userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        HashMap<String, String> res=new HashMap<>();
        res.put("Employee id :",""+employeeId);
        res.put("message : ","Employee  is deleted successfully..");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getstoreemployees/{storeId}")
    public ResponseEntity<List<UserModal>> getStoreEmployees(@PathVariable("storeId") Long storeId){
        return ResponseEntity.ok(employeeService.findStoreEmployees(storeId,null));
    }
    @GetMapping("/getbranchemployee/{branchId}")
    public ResponseEntity<List<UserModal>> getBranchEmployee(@PathVariable("branchId") Long branchId){
        return ResponseEntity.ok(employeeService.findBranchEmployees(branchId,null));
    }

    @GetMapping("/employeedetails/{empId}")
    public ResponseEntity<UserModal> getEmployeeDetails(@PathVariable("empId") Long empId){
        return ResponseEntity.ok(employeeService.getEmployeeDetails(empId,null));
    }



}
