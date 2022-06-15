package com.example.order.orders.item_group;


import com.example.order.item.Item;
import com.example.order.util.Validate;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
    private static final int DAYS_TO_ADD_IN_STOCK = 1;
    private static final int DAYS_TO_ADD_NO_STOCK = 7;

    private final UUID itemGroupId;
    private final Item item;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(UUID itemGroupId, Item item, int amount) {
        Validate.objectIsNotNull(item);
        this.itemGroupId = itemGroupId;
        this.item = item;
        this.amount = amount;
        this.shippingDate = calculateShippingDate();
    }

    private LocalDate calculateShippingDate() {
        int stock = item.getStock();
        if (stock < amount){
            return LocalDate.now().plusWeeks(DAYS_TO_ADD_NO_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_TO_ADD_IN_STOCK);
    }

    public UUID getItemGroupId() {
        return itemGroupId;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
