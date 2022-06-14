package com.example.order.user.customer;

import com.example.order.user.UserDTO;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;

public class CreateCustomerDTO extends UserDTO {
    public CreateCustomerDTO(Name name, String email, Address address, String phoneNumber) {
        super(name, email, address, phoneNumber);
    }
}
