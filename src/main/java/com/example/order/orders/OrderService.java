package com.example.order.orders;

import com.example.order.item.ItemService;
import com.example.order.user.customer.CustomerService;
import com.example.order.util.Validate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final CustomerService customerService;
    private final ItemService itemService;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderService(CustomerService customerService, ItemService itemService, OrderMapper orderMapper, OrderRepository orderRepository) {
        this.customerService = customerService;
        this.itemService = itemService;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public void addOrder(AddOrderDTO addOrderDTO) {
        Validate.objectIsNotNull(addOrderDTO);
        Order order = orderMapper.toEntity(addOrderDTO);
        orderRepository.addItem(order);
        customerService.addOrder(order);
    }
}
