package com.example.order.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {
    ItemService itemService;
    ItemRepository itemRepository;
    ItemMapper itemMapper;

    UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        itemRepository = new ItemRepository();
        itemMapper = new ItemMapper();
        itemService = new ItemService(itemRepository, itemMapper);
    }

    @Test
    @DisplayName("Given an item, when adding an item, the item is added")
    void givenAnItemWhenAddingAnItemTheItemIsAdded() {
        AddItemDTO addItemDTO = new AddItemDTO( "Shirt", "Just a shirt", 5, 3);

        ItemDTO itemDTO = itemService.addItem(addItemDTO);
        assertEquals(addItemDTO, itemDTO);
    }

    @Test
    @DisplayName("Given a null item, when adding an item, error thrown")
    void givenAnItemWhenAddingANullItemThenErrorIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> itemService.addItem(null));
    }

}
