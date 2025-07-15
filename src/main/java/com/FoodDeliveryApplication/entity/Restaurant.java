package com.FoodDeliveryApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Restaurant")
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "phone is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10-digit Indian number starting with 6-9"
    )
    private String phone;

    @NotBlank(message = "openTime is required")
    private String openTime;

    @NotBlank(message = "closeTime is required")
    private String closeTime;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menuItems = new ArrayList<>();
}
