package com.FoodDeliveryApplication.service.impl;

import com.FoodDeliveryApplication.repository.RestaurantRepository;
import com.FoodDeliveryApplication.entity.Restaurant;
import com.FoodDeliveryApplication.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }
    @Override
    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));

        restaurant.setName(updatedRestaurant.getName());
        restaurant.setDescription(updatedRestaurant.getDescription());
        restaurant.setAddress(updatedRestaurant.getAddress());
        restaurant.setPhone(updatedRestaurant.getPhone());
        restaurant.setOpenTime(updatedRestaurant.getOpenTime());
        restaurant.setCloseTime(updatedRestaurant.getCloseTime());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

}
