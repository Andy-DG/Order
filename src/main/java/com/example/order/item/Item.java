package com.example.order.item;

import com.example.order.util.ErrorSpecification;
import com.example.order.util.Validate;

import java.util.Objects;
import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final String description;
    private double price;
    private int stock;

    public Item(UUID id, String name, String description, double price, int stock) {
        validateAmount(stock);
        validatePrice(price);
        Validate.stringIsNotEmptyOrNull(new ErrorSpecification("Name and description "), name, description);
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    private void validatePrice(double price) {
        Validate.numberIsNotNegative(new ErrorSpecification("Price "),price);
        Validate.numberIsNotZero(new ErrorSpecification("Price "), price);
    }

    private void validateAmount(int amount) {
        Validate.numberIsNotNegative(new ErrorSpecification("Amount "), amount);
        Validate.numberIsNotZero(new ErrorSpecification("Amount "),amount);
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
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
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (Double.compare(item.price, price) != 0) return false;
        if (stock != item.stock) return false;
        if (!Objects.equals(id, item.id)) return false;
        if (!Objects.equals(name, item.name)) return false;
        return Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + stock;
        return result;
    }
}
