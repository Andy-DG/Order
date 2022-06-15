package com.example.order;

import com.example.order.item.ItemService;
import com.example.order.user.customer.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final CustomerService customerService;
    private final ItemService itemService;

    public OrderService(CustomerService customerService, ItemService itemService) {
        this.customerService = customerService;
        this.itemService = itemService;
    }
}
