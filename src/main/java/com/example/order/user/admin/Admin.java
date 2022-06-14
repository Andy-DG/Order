package com.example.order.user.admin;

import com.example.order.user.User;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;

public class Admin extends User {
    public Admin(Name name, String email, Address address, String phoneNumber) {
        super(name, email, address, phoneNumber);
    }
}
