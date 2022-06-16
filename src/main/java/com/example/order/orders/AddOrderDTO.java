package com.example.order.orders;

import com.example.order.orders.item_group.ItemGroup;
import com.example.order.user.customer.Customer;

import java.util.List;
import java.util.UUID;

public class AddOrderDTO {
    private final UUID id;
    private final List<ItemGroup> itemGroups;
    private final Customer customer;
    private final double totalPrice;

    public AddOrderDTO(List<ItemGroup> itemGroups, Customer customer, double totalPrice) {
        this.id = UUID.randomUUID();
        this.itemGroups = itemGroups;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public AddOrderDTO(UUID id, List<ItemGroup> itemGroups, Customer customer, double totalPrice) {
        this.id = id;
        this.itemGroups = itemGroups;
        this.customer = customer;
        this.totalPrice = totalPrice;
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
