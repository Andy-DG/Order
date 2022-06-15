package com.example.order.item;

import com.example.order.util.Validate;

import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final String description;
    private final double price;
    private int amount;

    protected Item(UUID id, String name, String description, double price, int amount) {
        validateAmount(amount);
        validatePrice(price);
        Validate.stringIsNotEmptyOrNull(name, description);
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    private void validatePrice(double price) {
        Validate.numberIsNotNegative(price);
        Validate.numberIsNotZero(price);
    }

    private void validateAmount(int amount) {
        Validate.numberIsNotNegative(amount);
        Validate.numberIsNotZero(amount);
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
