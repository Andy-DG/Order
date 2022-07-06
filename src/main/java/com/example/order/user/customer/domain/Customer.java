package com.example.order.user.customer.domain;

import com.example.order.user.User;
import com.example.order.user.footing.domain.Address;
import com.example.order.user.footing.domain.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends User {
    public Customer() {}

    public Customer(Name name, String email, Address address, String phoneNumber) {
        super(name, email, address, phoneNumber);
    }

}
