package com.example.order.item;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toEntity(AddItemDTO addItemDTO) {
        return new Item(addItemDTO.getId(), addItemDTO.getName(), addItemDTO.getDescription(), addItemDTO.getPrice(), addItemDTO.getStock());
    }

    public ItemDTO toDTO(Item item) {
        return new ItemDTO(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStock());
    }

    public AddItemDTO toAddItemDTO(Item item) {
        return new AddItemDTO(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStock());
    }

}
