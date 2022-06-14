package com.example.order.user.customer;

import com.example.order.user.User;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;

import java.util.UUID;

public class Customer extends User {
    public Customer(Name name, String email, Address address, String phoneNumber) {
        super(name, email, address, phoneNumber);
    }

    public Customer(UUID id, Name name, String email, Address address, String phoneNumber) {
        super(id, name, email, address, phoneNumber);
    }
}
