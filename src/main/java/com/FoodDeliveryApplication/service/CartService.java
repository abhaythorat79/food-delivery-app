package com.FoodDeliveryApplication.service;

import com.FoodDeliveryApplication.entity.Cart;

public interface CartService {
    Cart addToCart(Long userId, Long menuId, int quantity);
    Cart removeFromCart(Long userId, Long menuId);
    Cart viewCart(Long userId);
}
