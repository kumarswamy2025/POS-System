package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.CustomerService;
import com.possystem.mainapplication.Service.Services.StoreService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.exceptions.CustomerException.CustomerException;
import com.possystem.mainapplication.modal.CustomerModal;
import com.possystem.mainapplication.repository.CategoryRepo;
import com.possystem.mainapplication.repository.CustomerResp;
import com.possystem.mainapplication.repository.StoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerService {
    //    here we inject repos

    private final CustomerResp customerResp;


    //    here we inject services



    @Override
    public CustomerModal createCustomer(CustomerModal customerModal) {
        CustomerModal customerModal1=CustomerModal.builder()
                .fullName(customerModal.getFullName())
                .email(customerModal.getEmail())
                .phone(customerModal.getPhone())
                .build();
//        save data to DB
        CustomerModal savedData=customerResp.save(customerModal1);
        return savedData;
    }

    @Override
    public CustomerModal updateCustomer(Long id, CustomerModal customerModal) {
//        checking if customer is exits or not
        CustomerModal modal=customerResp.findById(id).orElseThrow(
                ()-> new CustomerException("customer not found...", HttpStatus.NOT_FOUND)
        );

        if(customerModal.getFullName()!=null){
            modal.setFullName(customerModal.getFullName());
        }


        if(customerModal.getEmail()!=null){
            modal.setEmail(customerModal.getEmail());
        }


        if(customerModal.getPhone()!=null){
            modal.setPhone(customerModal.getPhone());
        }

//        save data to DB
       CustomerModal savedData=customerResp.save(modal);


        return savedData;
    }

    @Override
    public void deleteCustomer(Long id) {
//        checking if customer is exits or not
        CustomerModal modal=customerResp.findById(id).orElseThrow(
                ()-> new CustomerException("customer not found...", HttpStatus.NOT_FOUND)
        );

        customerResp.delete(modal);
    }

    @Override
    public CustomerModal getCustomer(Long id) {
        //        checking if customer is exits or not
        CustomerModal modal=customerResp.findById(id).orElseThrow(
                ()-> new CustomerException("customer not found...", HttpStatus.NOT_FOUND)
        );
        return modal;
    }

    @Override
    public List<CustomerModal> getAllCustomers() {
        List<CustomerModal> customerModals=customerResp.findAll();
        return customerModals;
    }

    @Override
    public List<CustomerModal> searchCustomer(String keyword) {
        List<CustomerModal> customerModals=customerResp.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword,keyword);
        return customerModals;
    }
}
