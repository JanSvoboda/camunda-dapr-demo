package com.example.registercustomer.service.impl;

import com.example.registercustomer.domain.model.Customer;
import com.example.registercustomer.repository.CustomerRepository;
import com.example.registercustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).isPresent() ? customerRepository.findById(id).get() : null;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
