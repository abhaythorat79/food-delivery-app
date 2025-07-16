package com.FoodDeliveryApplication.service.impl;

import com.FoodDeliveryApplication.entity.Cart;
import com.FoodDeliveryApplication.entity.CartItem;
import com.FoodDeliveryApplication.entity.Menu;
import com.FoodDeliveryApplication.entity.User;
import com.FoodDeliveryApplication.exception.ResourceNotFoundException;
import com.FoodDeliveryApplication.repository.CartItemRepository;
import com.FoodDeliveryApplication.repository.CartRepository;
import com.FoodDeliveryApplication.repository.MenuRepository;
import com.FoodDeliveryApplication.repository.UserRepository;
import com.FoodDeliveryApplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart addToCart(Long userId, Long menuId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", menuId));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        // Check if item already exists
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getMenu().getId().equals(menuId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setMenu(menu);
            item.setQuantity(quantity);
            cart.getCartItems().add(item);
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart removeFromCart(Long userId, Long menuId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "User", userId));

        cart.getCartItems().removeIf(item -> item.getMenu().getId().equals(menuId));

        return cartRepository.save(cart);
    }

    @Override
    public Cart viewCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "User", userId));
    }

}
