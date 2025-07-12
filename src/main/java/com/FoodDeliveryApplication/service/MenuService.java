package com.FoodDeliveryApplication.service;

import com.FoodDeliveryApplication.entity.Menu;
import com.FoodDeliveryApplication.enums.Category;

import java.util.List;

public interface MenuService {
    Menu createMenu(Menu menu);
    List<Menu> getMenusByCategory(Category category);
}
