package com.example.order.user.customer;

import com.example.order.user.UserDTO;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;

import java.util.UUID;


public class CustomerDTO extends UserDTO {
    public CustomerDTO(UUID id, Name name, String email, Address address, String phoneNumber) {
        super(id, name, email, address, phoneNumber);
    }
}
