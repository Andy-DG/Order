package com.example.order.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Customer registerCustomer(CustomerDTO customerDTO) {
        Customer customer = this.customerMapper.toEntity(customerDTO);
        return this.customerRepository.register(customer);
    }
}
