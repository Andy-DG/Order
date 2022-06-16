package com.example.order.item;

import java.util.Objects;
import java.util.UUID;

public class ItemDTO extends AbstractItemDTO{
    public ItemDTO(UUID id, String name, String description, double price, int stock) {
        super(id, name, description, price, stock);
    }
}
