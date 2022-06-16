package com.example.order.orders;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(AddOrderDTO addOrderDTO) {
        return new Order(addOrderDTO.getId(), addOrderDTO.getItemGroups(), addOrderDTO.getCustomer());
    }

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(order.getId(), order.getItemGroups(), order.getCustomer(), order.getTotalPrice());
    }

    public AddOrderDTO toAddOrderDTO(Order order) {
        return new AddOrderDTO(order.getId(), order.getItemGroups(), order.getCustomer(), order.getTotalPrice());
    }
}
