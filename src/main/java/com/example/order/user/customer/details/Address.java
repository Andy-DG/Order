package com.example.order.user.customer.details;

import com.example.order.util.Validate;

import java.util.Objects;

public record Address(String streetName, int streetNumber, int postalCode, String city) {
    public Address {
        Validate.stringIsNotEmptyOrNull(streetName);
        Validate.numberIsNotNegative(streetNumber);
        Validate.numberIsNotZero(streetNumber);
        Validate.numberIsNotNegative(postalCode);
        Validate.numberIsNotZero(postalCode);
        Validate.stringIsNotEmptyOrNull(city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                '}';
    }
}
