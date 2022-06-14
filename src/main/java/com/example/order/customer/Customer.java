package com.example.order.customer;

import com.example.order.util.Validate;

public class Customer {
    private final Name name;
    private final String email;
    private final Address address;
    private final String phoneNumber;

    public Customer(Name name, String email, Address address, String phoneNumber) {
        Validate.validateEmail(email);
        Validate.validatePhoneNumber(phoneNumber);

        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
