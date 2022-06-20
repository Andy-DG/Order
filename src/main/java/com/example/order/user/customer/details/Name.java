package com.example.order.user.customer.details;

import com.example.order.util.ErrorSpecification;
import com.example.order.util.Validate;

public record Name(String firstName, String lastName) {
    public Name {
        Validate.stringIsNotEmptyOrNull(new ErrorSpecification("First and last-name "), firstName, lastName);
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
