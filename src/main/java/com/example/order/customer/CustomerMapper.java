package com.example.order.customer;

import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {
    CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.id(), customer.name(), customer.email(), customer.address(), customer.phoneNumber());
    }

    Customer toEntity(CustomerDTO customerDTO) {
        return new Customer(customerDTO.id(), customerDTO.name(), customerDTO.email(), customerDTO.address(), customerDTO.phoneNumber());
    }
}
