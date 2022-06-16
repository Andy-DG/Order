package com.example.order.user.customer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomerMapper {
    CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    List<CustomerDTO> toDTO(List<Customer> customers) {
        return customers.stream().map(this::toDTO).toList();
    }

    Customer toEntity(CreateCustomerDTO createCustomerDTO) {
        return new Customer(createCustomerDTO.getId(), createCustomerDTO.getName(), createCustomerDTO.getEmail(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
    }
}
