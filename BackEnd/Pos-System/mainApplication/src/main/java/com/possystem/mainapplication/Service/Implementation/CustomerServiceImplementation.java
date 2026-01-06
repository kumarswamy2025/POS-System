package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.CustomerService;
import com.possystem.mainapplication.modal.CustomerModal;

import java.util.List;


public class CustomerServiceImplementation implements CustomerService {
    @Override
    public CustomerModal createCustomer(CustomerModal customerModal) {
        return null;
    }

    @Override
    public CustomerModal updateCustomer(Long id, CustomerModal customerModal) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public CustomerModal getCustomer(Long id) {
        return null;
    }

    @Override
    public List<CustomerModal> getAllCustomers() {
        return List.of();
    }

    @Override
    public List<CustomerModal> searchCustomer(String keyword) {
        return List.of();
    }
}
