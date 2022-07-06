package com.example.order.user.customer.service;

import com.example.order.user.customer.api.dto.CreateCustomerDTO;
import com.example.order.user.customer.api.dto.CustomerDTO;
import com.example.order.user.customer.domain.Customer;
import com.example.order.user.footing.service.AddressMapper;
import com.example.order.user.footing.service.NameMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomerMapper {

    private final AddressMapper addressMapper;
    private final NameMapper nameMapper;

    public CustomerMapper(AddressMapper addressMapper, NameMapper nameMapper) {
        this.addressMapper = addressMapper;
        this.nameMapper = nameMapper;
    }

    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO()
                .setId(customer.getId())
                .setName(nameMapper.toDTO(customer.getName()))
                .setEmail(customer.getEmail())
                .setAddress(addressMapper.toDTO(customer.getAddress()))
                .setPhoneNumber(customer.getPhoneNumber());
    }

    public List<CustomerDTO> toDTO(List<Customer> customers) {
        return customers.stream().map(this::toDTO).toList();
    }

    public Customer toEntity(CreateCustomerDTO createCustomerDTO) {
        return new Customer(
                nameMapper.toEntity(createCustomerDTO.getName()),
                createCustomerDTO.getEmail(),
                addressMapper.toEntity(createCustomerDTO.getAddressDTO()),
                createCustomerDTO.getPhoneNumber());
    }
}
