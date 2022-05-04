package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Category;

public interface ICategoryService {
    Category findByCategory(Integer category);

}
