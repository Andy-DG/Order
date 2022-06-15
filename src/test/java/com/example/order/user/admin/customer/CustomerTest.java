package com.example.order.user.admin.customer;

import com.example.order.user.customer.Customer;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    UUID id = UUID.randomUUID();

    @Test
    @DisplayName("givenARightPhoneNumber_whenCreatingAMember_ThePhoneNumberIsEqual")
    void givenARightPhoneNumberWhenCreatingAMemberThePhoneNumberIsEqual() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        String email = "morty@smith.com";
        String phoneNumber = "+111 (202) 555-0125";
        Customer customer = new Customer(name,
                email,
                address,
                phoneNumber);
        assertEquals(phoneNumber, customer.getPhoneNumber());
    }

    @Test
    @DisplayName("givenARightEmailAddress_whenCreatingAMember_TheEmailAddressIsEqual")
    void givenARightEmailAddressWhenCreatingAMemberTheEmailAddressIsEqual() {
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        String email = "morty@smith.com";
        String phoneNumber = "+111 (202) 555-0125";
        Customer customer = new Customer(name,
                email,
                address,
                phoneNumber);
        assertEquals(phoneNumber, customer.getPhoneNumber());
    }

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