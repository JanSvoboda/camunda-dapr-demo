package com.example.registercustomer.service;

import com.example.registercustomer.domain.model.Customer;

public interface CustomerService {
    Customer findCustomerById(Long id);
    Customer saveCustomer(Customer customer);
}
