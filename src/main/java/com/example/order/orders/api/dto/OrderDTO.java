package com.example.order.orders.api.dto;

import com.example.order.item_group.api.dto.ItemGroupDTO;
import com.example.order.item_group.domain.ItemGroup;
import com.example.order.user.customer.domain.Customer;

import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private String id;
    private List<ItemGroupDTO> itemGroupDTOList;
    private String customerId;
    private double totalPrice;

    public OrderDTO setId(String id) {
        this.id = id;
        return this;
    }

    public OrderDTO setItemGroupDTOList(List<ItemGroupDTO> itemGroupDTOList) {
        this.itemGroupDTOList = itemGroupDTOList;
        return this;
    }

    public OrderDTO setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderDTO setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String getId() {
        return id;
    }

    public List<ItemGroupDTO> getItemGroupDTOList() {
        return itemGroupDTOList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", itemGroupDTOList=" + itemGroupDTOList +
                ", customerId=" + customerId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
