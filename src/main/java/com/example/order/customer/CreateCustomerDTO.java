package com.example.order.customer;

import com.example.order.customer.details.Address;
import com.example.order.customer.details.Name;
import com.example.order.util.Validate;

import java.util.UUID;

public class CreateCustomerDTO {
    private final UUID id;
    private final Name name;
    private final String email;
    private final Address address;
    private final String phoneNumber;


    public CreateCustomerDTO(Name name, String email, Address address, String phoneNumber) {
        Validate.validateEmail(email);
        Validate.validatePhoneNumber(phoneNumber);
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
