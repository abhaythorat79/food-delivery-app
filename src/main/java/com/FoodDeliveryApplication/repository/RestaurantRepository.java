package com.FoodDeliveryApplication.repository;

import com.FoodDeliveryApplication.entity.Menu;
import com.FoodDeliveryApplication.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


}
