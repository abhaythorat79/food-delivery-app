package com.FoodDeliveryApplication.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {

    private Long id;

    @NotBlank(message = "Menu name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category; // e.g., VEG, NON-VEG, DRINKS

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be non-negative")
    private Double price;

    private String imageUrl;

    @NotNull(message = "Restaurant ID is required")
    private Long restaurantId;
}