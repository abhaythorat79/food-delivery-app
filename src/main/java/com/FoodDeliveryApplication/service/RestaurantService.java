package com.FoodDeliveryApplication.service;

import com.FoodDeliveryApplication.entity.Restaurant;

import java.util.List;


public interface RestaurantService {

    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Long id, Restaurant restaurant);
    void deleteRestaurant(Long id);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(Long id);

}
