package com.example.order.orders;

import com.example.order.item.Item;
import com.example.order.util.Validate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderRepository {
    private Map<UUID, Order> orderMap;

    public OrderRepository() {
        this.orderMap = new HashMap<>();
    }

    public void addItem(Order order) {
        Validate.objectIsNotNull(order);
        if (orderAlreadyExists(order)) {
            throw new IllegalArgumentException("An item with this name already exists. Use the update item functionality.");
        }
        orderMap.put(order.getId(), order);
    }

    public boolean orderAlreadyExists(Order orderToAdd) {
        return getOrderById(orderToAdd.getId()) != null;
    }

    public Order getOrderById(UUID orderId) {
        return orderMap.values().stream().filter(order -> order.getId() == orderId).findFirst().orElse(null);
    }
}
