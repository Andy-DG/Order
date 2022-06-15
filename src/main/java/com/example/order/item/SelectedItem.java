package com.example.order.item;

import java.util.UUID;

public record SelectedItem(UUID id, String name, String description, double price, int stock) {

}
