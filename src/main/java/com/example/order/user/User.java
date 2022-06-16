package com.example.order.user;

import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import com.example.order.util.Validate;

import java.util.Objects;
import java.util.UUID;

public abstract class User {
    private final UUID id;
    private final Name name;
    private final String email;
    private final Address address;
    private final String phoneNumber;

    protected User(Name name, String email, Address address, String phoneNumber) {
        Validate.validateEmail(email);
        Validate.validatePhoneNumber(phoneNumber);
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    protected User(UUID id, Name name, String email, Address address, String phoneNumber) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(address, user.address) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address, phoneNumber);
    }
}
