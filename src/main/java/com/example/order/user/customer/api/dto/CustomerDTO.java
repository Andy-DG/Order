package com.example.order.user.customer.api.dto;

import com.example.order.user.footing.api.dto.AddressDTO;
import com.example.order.user.footing.api.dto.NameDTO;
import com.example.order.user.footing.domain.Address;
import com.example.order.user.footing.domain.Name;

import java.util.Objects;

public class CustomerDTO {
    private String id;
    private NameDTO nameDTO;
    private String email;
    private AddressDTO addressDTO;
    private String phoneNumber;

    public CustomerDTO setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerDTO setName(NameDTO nameDTO) {
        this.nameDTO = nameDTO;
        return this;
    }

    public CustomerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDTO setAddress(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
        return this;
    }

    public CustomerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getId() {
        return id;
    }

    public NameDTO getNameDTO() {
        return nameDTO;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name=" + nameDTO +
                ", email='" + email + '\'' +
                ", address=" + addressDTO +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
