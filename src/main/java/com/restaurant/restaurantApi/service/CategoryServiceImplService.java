package com.restaurant.restaurantApi.service;

import com.restaurant.restaurantApi.model.Category;
import com.restaurant.restaurantApi.repo.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImplService implements ICategoryService {

    @Autowired
    private ICategoryRepo categoryRepo;

    @Override
    public Category findByCategory(Integer category) {
        return this.categoryRepo.findByCategory(category);
    }
}
