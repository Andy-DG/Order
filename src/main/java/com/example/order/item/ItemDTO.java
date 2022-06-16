package com.example.order.item;

import java.util.Objects;
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

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        ItemDTO itemDTO = (ItemDTO) o;

        if (Double.compare(itemDTO.price, price) != 0) return false;
        if (stock != itemDTO.stock) return false;
        if (!Objects.equals(id, itemDTO.id)) return false;
        if (!Objects.equals(name, itemDTO.name)) return false;
        return Objects.equals(description, itemDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
