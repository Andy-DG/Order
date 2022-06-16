package com.example.order.orders.item_group;

import com.example.order.item.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;



class ItemGroupTest {
    UUID id = UUID.randomUUID();

    @Test
    @DisplayName("given an item and an amount, when creating an item group with an amount less than the stock, a shipping date of 1 day is created")
    void givenAnItemAndAnAmountWhenCreatingAnItemGroupAShippingDateIsCreated() {
        Item item = new Item(id,"Shirt", "A Shirt", 1.20, 5);
        ItemGroup itemGroup = new ItemGroup(id, item, 5);
        LocalDate actual = itemGroup.getShippingDate();
        LocalDate expected = LocalDate.now().plusDays(1);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("given an item and an amount, when creating an item group with an amount less than the stock, a shipping date of 7 days is created")
    void givenAnItemAndAnAmountWhenCreatingAnItemGroupWithAnAmpountLessThanTheStockAShippingDateOfSevenDaysIsCreated() {
        Item item = new Item(id,"Shirt", "A Shirt", 1.20, 5);
        ItemGroup itemGroup = new ItemGroup(id, item, 10);
        LocalDate actual = itemGroup.getShippingDate();
        LocalDate expected = LocalDate.now().plusDays(7);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("given an itemgroup, when the price of the original item changes, the price of the itemgroup does not change")
    void givenAnItemgroupWhenThePriceOfTheOriginalItemChangesThePriceOfTheItemgroupDoesNotChange() {
        Item item = new Item(id,"Shirt", "A Shirt", 1.20, 5);
        ItemGroup itemGroup = new ItemGroup(id, item, 5);

        item.setPrice(2.00);

        double actual = itemGroup.getSelectedItem().price();
        double expected = 1.2;
        assertEquals(expected,actual);

    }

}