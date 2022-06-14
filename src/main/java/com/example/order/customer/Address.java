package com.example.order.customer;

import com.example.order.util.Validate;

public record Address(String streetName, int streetNumber, int postalCode, String city) {
    public Address {
        Validate.stringIsNotEmptyOrNull(streetName);
        Validate.numberIsNotNegative(streetNumber);
        Validate.numberIsNotZero(streetNumber);
        Validate.numberIsNotNegative(postalCode);
        Validate.numberIsNotZero(postalCode);
        Validate.stringIsNotEmptyOrNull(city);
    }
}
