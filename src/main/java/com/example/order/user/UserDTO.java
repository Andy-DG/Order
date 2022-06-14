package com.example.order.user;

import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import com.example.order.util.Validate;

import java.util.UUID;

public abstract class UserDTO {
    private final UUID id;
    private final Name name;
    private final String email;
    private final Address address;
    private final String phoneNumber;


    protected UserDTO(Name name, String email, Address address, String phoneNumber) {
        Validate.validateEmail(email);
        Validate.validatePhoneNumber(phoneNumber);
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    protected UserDTO(UUID id, Name name, String email, Address address, String phoneNumber) {
        Validate.validateEmail(email);
        Validate.validatePhoneNumber(phoneNumber);
        this.id = id;
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
