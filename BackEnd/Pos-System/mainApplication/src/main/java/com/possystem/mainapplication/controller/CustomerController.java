package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.CustomerService;
import com.possystem.mainapplication.modal.CustomerModal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping("/create")
    public ResponseEntity<CustomerModal> create(CustomerModal customerModal){
        return  ResponseEntity.ok(customerService.createCustomer(customerModal));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerModal> update(@PathVariable("id") Long id, CustomerModal customerModal){
        return ResponseEntity.ok(customerService.updateCustomer(id,customerModal));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id ){
       customerService.deleteCustomer(id);
        HashMap<String, String> res=new HashMap<>();
        res.put("Customer id :",""+id);
        res.put("message : ","Customer  is deleted successfully..");
        return ResponseEntity.ok(res);
    }
    @GetMapping("/getcustomer/{id}")
    public ResponseEntity<CustomerModal> getCustomerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(customerService.getCustomer(id));
    }
    @GetMapping("/allcustomers")
    public ResponseEntity<List<CustomerModal>> allCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<CustomerModal>> searchCustomer(@PathVariable("keyword") String keyword){
        return ResponseEntity.ok(customerService.searchCustomer(keyword));
    }

}
