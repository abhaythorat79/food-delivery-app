package com.FoodDeliveryApplication.controller;


import com.FoodDeliveryApplication.entity.Menu;
import com.FoodDeliveryApplication.enums.Category;
import com.FoodDeliveryApplication.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // Create Menu Item
    @PostMapping
    public ResponseEntity<Menu> createMenu(@Valid @RequestBody Menu menu) {
        return new ResponseEntity<>(menuService.createMenu(menu), HttpStatus.CREATED);
    }

    // Get Menus by Category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Menu>> getMenusByCategory(@PathVariable String category) {
        try {
            Category catEnum = Category.valueOf(category.toUpperCase());
            return ResponseEntity.ok(menuService.getMenusByCategory(catEnum));
        } catch (IllegalArgumentException e) {
          //  throw new InvalidCategoryException("Invalid category: " + category);
        }
    }
}
