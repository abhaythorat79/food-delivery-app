package com.FoodDeliveryApplication.controller;

import com.FoodDeliveryApplication.dto.CartRequestDTO;
import com.FoodDeliveryApplication.entity.Cart;
import com.FoodDeliveryApplication.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody @Valid CartRequestDTO request) {
        Cart cart = cartService.addToCart(request.getUserId(), request.getMenuId(), request.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(@RequestBody @Valid CartRequestDTO request) {
        Cart cart = cartService.removeFromCart(request.getUserId(), request.getMenuId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/view/{userId}")
    public ResponseEntity<Cart> viewCart(@PathVariable Long userId) {
        Cart cart = cartService.viewCart(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
