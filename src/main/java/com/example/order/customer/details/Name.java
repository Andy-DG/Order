package com.example.order.customer.details;

import com.example.order.util.Validate;

public record Name(String firstName, String lastName) {
    public Name {
        Validate.stringIsNotEmptyOrNull(firstName);
        Validate.stringIsNotEmptyOrNull(lastName);
    }
}