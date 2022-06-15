package com.example.order.orders;

import com.example.order.orders.item_group.ItemGroup;
import com.example.order.user.customer.Customer;
import com.example.order.util.Validate;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final List<ItemGroup> itemGroups;
    private final Customer customer;
    private final double totalPrice;

    public Order(UUID id, List<ItemGroup> itemGroups, Customer customer, double totalPrice) {
        Validate.objectIsNotNull(itemGroups, customer);
        this.id = id;
        this.itemGroups = itemGroups;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public double calculateTotalPrice() {
        return itemGroups.stream().mapToDouble(itemGroup -> itemGroup.getItem().getPrice() * itemGroup.getAmount()).sum();
    }

    public UUID getId() {
        return id;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
