package com.restaurant.restaurantApi.model;

import lombok.Data;

import java.util.List;

@Data
public class Desk {
    private List<Order> orders;

}
