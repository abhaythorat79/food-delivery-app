package com.FoodDeliveryApplication.repository;

import com.FoodDeliveryApplication.entity.Menu;
import com.FoodDeliveryApplication.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCategory(Category category);

}
