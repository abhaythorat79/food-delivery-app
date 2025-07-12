package com.FoodDeliveryApplication.service.impl;

import com.FoodDeliveryApplication.entity.Menu;
import com.FoodDeliveryApplication.enums.Category;
import com.FoodDeliveryApplication.repository.MenuRepository;
import com.FoodDeliveryApplication.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getMenusByCategory(Category category) {
        return menuRepository.findByCategory(category);
    }
}
