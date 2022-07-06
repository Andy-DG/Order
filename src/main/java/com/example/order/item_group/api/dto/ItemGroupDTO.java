package com.example.order.item_group.api.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ItemGroupDTO {
    private String id;
    private String itemId;
    private int amount;
    private double pricePerItem;
    private LocalDate shippingDate;

    public ItemGroupDTO setId(String id) {
        this.id = id;
        return this;
    }

    public ItemGroupDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemGroupDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemGroupDTO setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
        return this;
    }

    public ItemGroupDTO setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemGroupDTO that = (ItemGroupDTO) o;

        return Objects.equals(itemId, that.itemId) && Double.compare(that.pricePerItem, pricePerItem) == 0 && (amount == that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, pricePerItem, amount);
    }
}
