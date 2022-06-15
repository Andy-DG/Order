package com.example.order.orders;

import com.example.order.orders.item_group.ItemGroup;
import com.example.order.user.customer.Customer;

import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private final UUID id;
    private final List<ItemGroup> itemGroup;
    private final Customer customer;
    private final double totalPrice;

    public OrderDTO(UUID id, List<ItemGroup> itemGroup, Customer customer, double totalPrice) {
        this.id = id;
        this.itemGroup = itemGroup;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public UUID getId() {
        return id;
    }

    public List<ItemGroup> getItemGroup() {
        return itemGroup;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
