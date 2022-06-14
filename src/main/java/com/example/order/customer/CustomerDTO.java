package com.example.order.customer;

import com.example.order.customer.details.Address;
import com.example.order.customer.details.Name;
import com.example.order.util.Validate;

public record CustomerDTO(String id, Name name, String email, Address address, String phoneNumber) {
    public CustomerDTO {
        Validate.validateEmail(email);
        Validate.validatePhoneNumber(phoneNumber);
        Validate.stringIsNotEmptyOrNull(id);
    }
}
