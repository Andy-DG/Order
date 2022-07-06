package com.example.order.item_group.service;

import com.example.order.item.domain.Item;
import com.example.order.item_group.api.dto.ItemGroupDTO;
import com.example.order.item_group.domain.ItemGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemGroupMapper {

    public ItemGroup toEntity(Item item, int amount) {
        return new ItemGroup(item, amount);
    }

    public ItemGroupDTO toDTO(ItemGroup itemGroup) {
        return new ItemGroupDTO()
                .setItemId(itemGroup.getId())
                .setItemId(itemGroup.getItem().getId())
                .setAmount(itemGroup.getAmount())
                .setPricePerItem(itemGroup.getPricePerItem())
                .setShippingDate(itemGroup.getShippingDate());
    }

    public List<ItemGroupDTO> toDTO(List<ItemGroup> itemGroups) {
        return itemGroups.stream().map(this::toDTO).toList();
    }
}
