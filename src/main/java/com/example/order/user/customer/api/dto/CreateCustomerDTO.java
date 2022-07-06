package com.example.order.user.customer.api.dto;

import com.example.order.user.footing.api.dto.AddressDTO;
import com.example.order.user.footing.api.dto.NameDTO;
import com.example.order.user.footing.domain.Name;

public class CreateCustomerDTO {
    private NameDTO name;
    private String email;
    private AddressDTO addressDTO;
    private String phoneNumber;

    public CreateCustomerDTO setName(NameDTO name) {
        this.name = name;
        return this;
    }

    public CreateCustomerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateCustomerDTO setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
        return this;
    }

    public CreateCustomerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public NameDTO getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
