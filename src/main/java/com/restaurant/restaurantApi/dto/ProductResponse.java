package com.restaurant.restaurantApi.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private String name;
    private String image;
    private Double price;
    private String  description;
}
