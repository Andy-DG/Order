package com.example.order.user.admin.domain;

import com.example.order.user.User;
import com.example.order.user.footing.domain.Address;
import com.example.order.user.footing.domain.Name;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")
public class Admin extends User {
    public Admin(Name name, String email, Address address, String phoneNumber) {
        super(name, email, address, phoneNumber);
    }

    public Admin() {

    }
}
