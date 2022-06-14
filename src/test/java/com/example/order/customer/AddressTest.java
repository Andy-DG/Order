package com.example.order.customer;

import com.example.order.user.customer.details.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void givenAnAdress_whenAllFieldsAreFilled_thenStreetNameIsOk() {
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertEquals("Morty-street", address.streetName());
    }

    @Test
    void givenAnAdress_whenAllFieldsAreFilled_thenStreetNumberIsOk() {
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertEquals(11, address.streetNumber());
    }

    @Test
    void givenAnAdress_whenAllFieldsAreFilled_thenPostalCodeIsOk() {
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertEquals(6910, address.postalCode());
    }

    @Test
    void givenAnAdress_whenAllFieldsAreFilled_thenCityIsOk() {
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        assertEquals("Seattle", address.city());
    }

    @Test
    void givenACityThatIsBlank_whenCreatingAddress_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->  new Address("Morty-street", 11, 6910, ""));
    }

    @Test
    void givenACityThatIsNull_whenCreatingAddress_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->  new Address("Morty-street", 11, 6910, null));
    }

    @Test
    void givenAStreetNameThatIsBlank_whenCreatingAddress_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->  new Address("", 11, 6910, "Seattle"));
    }

    @Test
    void givenAStreetNameThatIsNull_whenCreatingAddress_thenIllegalArgumentException() {
        String city = null;
        assertThrows(IllegalArgumentException.class, ()->  new Address(null, 11, 6910, "Seattle"));
    }

    @Test
    void givenAStreetNumberThatIsNegative_whenCreatingAddress_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->  new Address("Morty-street", -11, 6910, "Seattle"));
    }

    @Test
    void givenAStreetNumberThatIsZero_whenCreatingAddress_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->  new Address("Morty-street", 0, 6910, "Seattle"));
    }

    @Test
    void givenAPostalCodeThatIsNegative_whenCreatingAddress_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->  new Address("Morty-street", 11, -6910, "Seattle"));
    }

    @Test
    void givenAPostalCodeThatIsZero_whenCreatingAddress_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->  new Address("Morty-street", 11, 0, "Seattle"));
    }

}