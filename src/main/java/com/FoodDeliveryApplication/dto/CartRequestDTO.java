package com.FoodDeliveryApplication.dto;

import lombok.Data;

@Data
public class CartRequestDTO {
    private Long userId;
    private Long menuId;
    private int quantity;
}
