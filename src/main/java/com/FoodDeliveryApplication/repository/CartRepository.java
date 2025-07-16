package com.FoodDeliveryApplication.repository;

import com.FoodDeliveryApplication.entity.Cart;
import com.FoodDeliveryApplication.entity.CartItem;
import com.FoodDeliveryApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
        Optional<Cart> findByUser(User user);
    }



