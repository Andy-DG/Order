package com.example.order.orders.service;

import com.example.order.item_group.domain.ItemGroup;
import com.example.order.item_group.service.ItemGroupMapper;
import com.example.order.orders.api.dto.CreateOrderDTO;
import com.example.order.orders.api.dto.OrderDTO;
import com.example.order.orders.domain.Order;
import com.example.order.user.customer.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    private final ItemGroupMapper itemGroupMapper;

    public OrderMapper(ItemGroupMapper itemGroupMapper) {
        this.itemGroupMapper = itemGroupMapper;
    }

    public Order toEntity(Customer customer, List<ItemGroup> itemGroups) {
        return new Order(customer, itemGroups);
    }

    public OrderDTO toDTO(Order order) {
        return new OrderDTO()
                .setId(order.getId())
                .setCustomerId(order.getCustomer().getId())
                .setItemGroupDTOList(itemGroupMapper.toDTO(order.getItemGroups()))
                .setTotalPrice(order.getTotalPrice());
    }
}
