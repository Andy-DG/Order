package com.example.order.item.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class ItemDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
}
