package com.example.order.user.customer;

import com.example.order.user.customer.Customer;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    UUID id = UUID.randomUUID();

    @Test
    void givenAWrongEmailAddress_whenCreatingAMember_thenThrowsIllegalArgumentException() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(name,
                    "mortysmith.com",
                    address,
                    "+111 (202) 555-0125");
        });
    }

    @Test
    void givenAWrongPhoneNumber_whenCreatingAMember_thenThrowsIllegalArgumentException() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(name,
                    "morty@smith.com",
                    address,
                    "+1121 (202) 555-0125");
        });
    }

    @Test
    void givenAWrongEmailAddress_whenCreatingAMemberWithId_thenThrowsIllegalArgumentException() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(id, name,
                    "mortysmith.com",
                    address,
                    "+111 (202) 555-0125");
        });
    }

    @Test
    void givenAWrongPhoneNumber_whenCreatingAMemberWithId_thenThrowsIllegalArgumentException() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(id, name,
                    "morty@smith.com",
                    address,
                    "+1121 (202) 555-0125");
        });
    }

}