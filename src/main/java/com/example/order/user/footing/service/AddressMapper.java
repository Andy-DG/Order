package com.example.order.user.footing.service;

import com.example.order.user.footing.api.dto.AddressDTO;
import com.example.order.user.footing.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address toEntity(AddressDTO addressDTO) {
        return new Address(addressDTO.streetName(), addressDTO.streetNumber(), addressDTO.postalCode(), addressDTO.city());
    }

    public AddressDTO toDTO(Address address) {
        return new AddressDTO(address.streetName(), address.streetNumber(), address.postalCode(), address.city());
    }
}
