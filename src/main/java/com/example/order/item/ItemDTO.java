package com.example.order.item;

import java.util.UUID;

public class ItemDTO {
    private final UUID id;
    private final String name;
    private final String description;
    private final double price;
    private final int stock;

    protected ItemDTO(UUID id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
