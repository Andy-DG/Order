package com.example.order.user.footing.service;

import com.example.order.user.footing.api.dto.NameDTO;
import com.example.order.user.footing.domain.Name;
import org.springframework.stereotype.Component;

@Component
public class NameMapper {
    public Name toEntity(NameDTO nameDTO) {
        return new Name(nameDTO.firstName(), nameDTO.lastName());
    }

    public NameDTO toDTO(Name name) {
        return new NameDTO(name.firstName(), name.lastName());
    }
}
