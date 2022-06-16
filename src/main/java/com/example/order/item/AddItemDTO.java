package com.example.order.item;


import java.util.Objects;
import java.util.UUID;

public class AddItemDTO extends AbstractItemDTO{
    public AddItemDTO(String name, String description, double price, int stock) {
        super(name, description, price, stock);
    }
}
