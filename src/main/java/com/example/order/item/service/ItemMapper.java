package com.example.order.item.service;

import com.example.order.item.api.dto.CreateItemDTO;
import com.example.order.item.api.dto.ItemDTO;
import com.example.order.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item toEntity(CreateItemDTO createItemDTO) {
        return new Item(
                createItemDTO.getName(),
                createItemDTO.getDescription(),
                createItemDTO.getPrice(),
                createItemDTO.getStock());
    }

    public ItemDTO toDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .stock(item.getStock())
                .build();
    }
}
