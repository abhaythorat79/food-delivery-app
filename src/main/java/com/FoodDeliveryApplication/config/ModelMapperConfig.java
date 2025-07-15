package com.FoodDeliveryApplication.config;

import com.FoodDeliveryApplication.dto.MenuDTO;
import com.FoodDeliveryApplication.entity.Menu;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Map restaurantId → restaurant (MenuDTO to Menu)
        mapper.typeMap(MenuDTO.class, Menu.class).addMappings(m ->
                m.skip(Menu::setRestaurant) // we manually set this in the service
        );

        // Map restaurant → restaurantId (Menu to MenuDTO)
        mapper.typeMap(Menu.class, MenuDTO.class).addMapping(
                src -> src.getRestaurant().getId(),
                MenuDTO::setRestaurantId
        );

        return mapper;
    }
}
