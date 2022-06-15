package com.example.order.user.admin;

import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    @Test
    void givenAWrongEmailAddress_whenCreatingAMember_thenThrowsIllegalArgumentException() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertThrows(IllegalArgumentException.class, () -> {
            new Admin(name,
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
            new Admin(name,
                    "morty@smith.com",
                    address,
                    "+1121 (202) 555-0125");
        });
    }

}