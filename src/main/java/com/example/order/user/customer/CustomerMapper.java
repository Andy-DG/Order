package com.example.order.user.customer;

import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {
    CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    Customer toEntity(CreateCustomerDTO createCustomerDTO) {
        return new Customer(createCustomerDTO.getId(), createCustomerDTO.getName(), createCustomerDTO.getEmail(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
    }
}
