package com.restaurant.restaurantApi.service;

import com.restaurant.restaurantApi.model.Category;

public interface ICategoryService {
    Category findByCategory(Integer category);

}
