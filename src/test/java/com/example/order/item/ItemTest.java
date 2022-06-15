package com.example.order.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    UUID id = UUID.randomUUID();

    @Test
    @DisplayName("Given an item with 0 as price, throw an error")
    void givenAnItemWith0AsPriceThrowAnError() {
        assertThrows(IllegalArgumentException.class, () -> {
            Item item = new Item(id, "Shirt", "Just a shirt", 0, 3);
        });
    }

    @Test
    @DisplayName("Given an item with negative number as price, throw an error")
    void givenAnItemWithNegativeNumberAsPriceThrowAnError() {
        assertThrows(IllegalArgumentException.class, () -> {
            Item item = new Item(id, "Shirt", "Just a shirt", -50, 3);
        });
    }

    @Test
    @DisplayName("Given an item with 0 as amount, throw an error")
    void givenAnItemWithZeroAsAmountThrowAnError() {
        assertThrows(IllegalArgumentException.class, () -> {
            Item item = new Item(id, "Shirt", "Just a shirt", 5, 0);
        });
    }

    @Test
    @DisplayName("Given an item with a negative amount, throw an error")
    void givenAnItemWithANegativeAmountThrowAnError() {
        assertThrows(IllegalArgumentException.class, () -> {
            Item item = new Item(id, "Shirt", "Just a shirt", 5, -5);
        });
    }

}