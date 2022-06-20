package com.example.order.item;

import com.example.order.orders.item_group.ItemGroup;
import com.example.order.util.ErrorSpecification;
import com.example.order.util.Validate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ItemRepository {
    private Map<UUID, Item> itemMap;

    public ItemRepository() {
        this.itemMap = new HashMap<>();
    }

    public void addItem(Item item) throws IllegalArgumentException {
        Validate.objectIsNotNull(new ErrorSpecification("Item "), item);
        if (itemAlreadyExists(item)) {
            updateCurrentItemInMap(item);
        }
        itemMap.put(item.getId(), item);
    }

    private Item updateCurrentItemInMap(Item item) {
        Item itemFromMap = this.itemMap.get(item.getId());
        itemFromMap.setPrice(item.getPrice());
        itemFromMap.setStock(itemFromMap.getStock() + item.getStock());
        return itemFromMap;
    }

    public boolean itemAlreadyExists(Item itemToAdd) {
        return getItemById(itemToAdd.getId()) != null;
    }

    public Item getItemById(UUID itemId) {
        return itemMap.get(itemId);
    }

    public Item getItemByName(String name) {
        return itemMap.values().stream().filter(item -> item.getName().equals(name)).findFirst().orElse(null);
    }

    public Map<UUID, Item> getItemMap() {
        return itemMap;
    }

    public void subtractOrderAmountFromStock(ItemGroup itemGroup) {
        Item item = getItemById(itemGroup.getSelectedItem().id());
        int amount = itemGroup.getAmount();
        getItemById(item.getId()).setStock(subtractAmountFromStock(item, amount));
    }


    private int subtractAmountFromStock(Item item, int amount) {
        if (item.getStock() < amount) {
            return 0;
        }
        return item.getStock() - amount;
    }
}
