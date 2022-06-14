package com.example.order.user.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = this.customerMapper.toEntity(createCustomerDTO);
        this.customerRepository.register(customer);
    }
}
