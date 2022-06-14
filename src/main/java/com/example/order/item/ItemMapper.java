package com.example.order.item;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toEntity(AddItemDTO addItemDTO) {
        return new Item(addItemDTO.getId(), addItemDTO.getName(), addItemDTO.getDescription(), addItemDTO.getPrice(), addItemDTO.getAmount());
    }
}
