package com.FoodDeliveryApplication.controller;

import com.FoodDeliveryApplication.dto.MenuDTO;
import com.FoodDeliveryApplication.entity.Restaurant;
/*
import com.FoodDeliveryApplication.exception.InvalidCategoryException;
*/
import com.FoodDeliveryApplication.service.MenuService;
import com.FoodDeliveryApplication.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/restaurants")
public class RestaurantControler{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;


    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        restaurant.setId(null); // Ensures Hibernate treats it as a new record
        return restaurantService.createRestaurant(restaurant);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
           restaurantService.deleteRestaurant(id);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuDTO>> getMenuByRestaurant(@PathVariable Long restaurantId) {
        List<MenuDTO> menuList = menuService.getMenuByRestaurant(restaurantId);
        return ResponseEntity.ok(menuList);
    }


}
