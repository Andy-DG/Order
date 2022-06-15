package com.example.order.item;

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
        Validate.objectIsNotNull(item);
        if (itemAlreadyExists(item)) {
            throw new IllegalArgumentException("An item with this name already exists. Use the update item functionality.");
        }
        itemMap.put(item.getId(), item);
    }

    public boolean itemAlreadyExists(Item itemToAdd) {
        return getItemById(itemToAdd.getId()) != null;
    }

    public Item getItemById(UUID itemId) {
        return itemMap.values().stream().filter(item -> item.getId() == itemId).findFirst().orElse(null);
    }
}
