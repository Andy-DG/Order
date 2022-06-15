package com.example.order.user.customer;

import com.example.order.orders.Order;
import com.example.order.user.User;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer extends User {

    private List<Order> orderList;

    public Customer(Name name, String email, Address address, String phoneNumber) {
        super(name, email, address, phoneNumber);
        this.orderList = new ArrayList<>();
    }

    public Customer(UUID id, Name name, String email, Address address, String phoneNumber) {
        super(id, name, email, address, phoneNumber);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addCustomerOrder(Order order) {
        orderList.add(order);
    }
}
