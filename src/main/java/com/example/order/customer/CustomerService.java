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
    public Customer registerCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = this.customerMapper.toEntity(createCustomerDTO);
        return this.customerRepository.register(customer);
    }
}
