package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.modal.CustomerModal;

import java.util.List;

public interface CustomerService {
    CustomerModal createCustomer(CustomerModal customerModal);
    CustomerModal updateCustomer(Long id,CustomerModal customerModal);
    void  deleteCustomer(Long id );
    CustomerModal getCustomer(Long id);
    List<CustomerModal> getAllCustomers() ;
    List<CustomerModal> searchCustomer(String keyword);

}
