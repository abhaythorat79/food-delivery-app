package com.FoodDeliveryApplication.repository;

import com.FoodDeliveryApplication.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
