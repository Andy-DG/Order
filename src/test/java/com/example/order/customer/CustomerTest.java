package com.example.order.customer;

import com.example.order.customer.details.Address;
import com.example.order.customer.details.Name;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void givenAWrongEmailAddress_whenCreatingAMember_thenThrowsIllegalArgumentException() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("123", name,
                    "mortysmith.com",
                    address,
                    "+111 (202) 555-0125");
        });
    }

    @Test
    void givenAnEmptyId_whenCreatingAMember_thenThrowsIllegalArgumentException() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer("123", name,
                    "morty@smith.com",
                    address,
                    "+1121 (202) 555-0125");
        });
    }

    @Test
    void givenANullId_whenCreatingAMember_thenThrowsIllegalArgumentException() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(null, name,
                    "morty@smith.com",
                    address,
                    "+111 (202) 555-0125");
        });
    }

    @Test
    void givenAWrongPhoneNumber_whenCreatingAMember_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Name name = new Name("Morty", "Smith");
            Address address = new Address("Morty-street", 11, 6910, "Seattle");
            new Customer("", name,
                    "morty@smith.com",
                    address,
                    "+111 (202) 555-0125");
        });
    }

}