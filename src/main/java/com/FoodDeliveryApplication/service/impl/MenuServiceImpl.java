package com.FoodDeliveryApplication.service.impl;

import com.FoodDeliveryApplication.dto.MenuDTO;
import com.FoodDeliveryApplication.entity.Menu;
import com.FoodDeliveryApplication.entity.Restaurant;
import com.FoodDeliveryApplication.enums.Category;
import com.FoodDeliveryApplication.exception.ResourceNotFoundException;
import com.FoodDeliveryApplication.repository.MenuRepository;
import com.FoodDeliveryApplication.repository.RestaurantRepository;
import com.FoodDeliveryApplication.service.MenuService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Menu> getMenusByCategory(Category category) {
        return menuRepository.findByCategory(category);
    }


    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public MenuDTO createMenu(MenuDTO menuDTO) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(menuDTO.getRestaurantId());
        Menu menu = modelMapper.map(menuDTO, Menu.class);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            menu.setRestaurant(restaurant); // ✅ use restaurant inside this block

            // proceed with restaurant
        } else {
            // handle not found manually (log, throw, return, etc.)
            System.out.println("Restaurant not found");
        }

        Menu savedMenu = menuRepository.save(menu);
        return modelMapper.map(savedMenu, MenuDTO.class);
    }

    @Override
    public List<MenuDTO> getMenuByRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurantId));

        List<Menu> menuItems = menuRepository.findByRestaurant(restaurant);

        return menuItems.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

}
