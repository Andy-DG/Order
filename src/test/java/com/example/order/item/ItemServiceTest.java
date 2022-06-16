package com.example.order.item;

import com.example.order.orders.item_group.ItemGroup;
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

    @Test
    @DisplayName("given an item, when adding the item, then the item is in the repository")
    void givenAnItemWhenAddingTheItemThenTheItemIsInTheRepository() {
        Item item = new Item(id,"Shirt", "A Shirt", 5.2, 5);
        AddItemDTO addItemDTO = itemMapper.toAddItemDTO(item);
        itemService.addItem(addItemDTO);
        Item actual = itemRepository.getItemById(id);
        assertEquals(item,actual);

    }

    @Test
    @DisplayName("given an item when we remove some stock the stock is removed")
    void givenAnItemWhenWeRemoveSomeStockTheStockIsRemoved() {
        Item item = new Item(id,"Shirt", "A Shirt", 5.2, 5);
        AddItemDTO addItemDTO = itemMapper.toAddItemDTO(item);

        itemService.addItem(addItemDTO);

        ItemGroup itemGroup = new ItemGroup(id, item, 3);

        //when
        itemService.subtractOrderAmountFromStock(itemGroup);
        //then
        int actual = itemRepository.getItemById(id).getStock();
        int expected = 5-3;
        assertEquals(expected,actual);

    }

}
