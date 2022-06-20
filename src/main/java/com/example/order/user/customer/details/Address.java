package com.example.order.user.customer.details;

import com.example.order.util.ErrorSpecification;
import com.example.order.util.Validate;

import java.util.Objects;

public record Address(String streetName, int streetNumber, int postalCode, String city) {
    public Address {
        Validate.stringIsNotEmptyOrNull(new ErrorSpecification("Street-name "),streetName);
        Validate.numberIsNotNegative(new ErrorSpecification("Street-number "), streetNumber);
        Validate.numberIsNotZero(new ErrorSpecification("Street-number "), streetNumber);
        Validate.numberIsNotNegative(new ErrorSpecification("Postal code "), postalCode);
        Validate.numberIsNotZero(new ErrorSpecification("Postal code "), postalCode);
        Validate.stringIsNotEmptyOrNull(new ErrorSpecification("City "), city);
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
