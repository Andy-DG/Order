package com.example.order.customer;

import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {
    CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    Customer toEntity(CreateCustomerDTO createCustomerDTO) {
        return new Customer(createCustomerDTO.getName(), createCustomerDTO.getEmail(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
    }
}
