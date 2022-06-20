package com.example.order.orders.item_group;


import com.example.order.item.Item;
import com.example.order.item.SelectedItem;
import com.example.order.util.ErrorSpecification;
import com.example.order.util.Validate;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
    private static final int DAYS_TO_ADD_IN_STOCK = 1;
    private static final int DAYS_TO_ADD_NO_STOCK = 7;

    private final UUID itemGroupId;
    private final SelectedItem selectedItem;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(UUID itemGroupId, Item item, int amount) {
        Validate.objectIsNotNull(new ErrorSpecification("Item "), item);
        this.itemGroupId = itemGroupId;
        this.selectedItem = new SelectedItem(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getStock());
        this.amount = amount;
        this.shippingDate = calculateShippingDate();
    }

    private LocalDate calculateShippingDate() {
        int stock = selectedItem.stock();
        if (stock < amount){
            return LocalDate.now().plusDays(DAYS_TO_ADD_NO_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_TO_ADD_IN_STOCK);
    }

    public UUID getItemGroupId() {
        return itemGroupId;
    }

    public SelectedItem getSelectedItem() {
        return selectedItem;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

}
