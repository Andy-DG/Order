package com.example.order.item;

import com.example.order.orders.item_group.ItemGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository;
    UUID id = UUID.randomUUID();
    UUID id2 = UUID.randomUUID();
    @BeforeEach
    void setUp(){
        itemRepository = new ItemRepository();
    }

    @Test
    void givenNoItemsInStock_whenAddingAnItem_ok(){
        Item item = new Item(id,"Shirt", "A Shirt", 5.2, 5);

        itemRepository.addItem(item);

        assertTrue(itemRepository.getItemMap().containsKey(item.getId()));
    }

    @Test
    void givenTwoDifferentItems_whenAddingItems_ok(){
        Item item = new Item(id,"Shirt", "A Shirt", 5.2, 5);
        Item item2 = new Item(id2,"Sweater", "A Sweater", 5.9, 3);

        itemRepository.addItem(item);
        itemRepository.addItem(item2);

        assertTrue(itemRepository.getItemMap().containsKey(item.getId()));
        assertTrue(itemRepository.getItemMap().containsKey(item2.getId()));
    }

    @Test
    void GivenAnItemWhenUpdatingTheStockAmountGetsSubtracted(){
        int item1Stock = 5;
        int itemAmount = 3;

        Item item = new Item(id,"Shirt", "A Shirt", 5.2, 5);
        ItemGroup itemGroup = new ItemGroup(id, item, 3);

        itemRepository.addItem(item);
        itemRepository.subtractOrderAmountFromStock(itemGroup);

        assertTrue(this.itemRepository.getItemMap().containsKey(item.getId()));
        assertEquals((item1Stock - itemAmount), this.itemRepository.getItemMap().get(item.getId()).getStock());

    }

    @Test
    void whenAddingTheSameItem_thenUpdateTheStock(){
        int item1Stock = 5;

        Item item = new Item(id,"Apple", "An apple", 1.20, item1Stock);

        itemRepository.addItem(item);
        itemRepository.addItem(item);

        assertTrue(this.itemRepository.getItemMap().containsKey(item.getId()));
        assertEquals((item1Stock + item1Stock), this.itemRepository.getItemMap().get(item.getId()).getStock());

    }


}