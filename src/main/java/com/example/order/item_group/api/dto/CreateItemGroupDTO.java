package com.example.order.item_group.api.dto;

public class CreateItemGroupDTO {
    private String itemId;
    private int amount;

    public CreateItemGroupDTO setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public CreateItemGroupDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "CreateItemGroupDTO{" +
                "itemId='" + itemId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
