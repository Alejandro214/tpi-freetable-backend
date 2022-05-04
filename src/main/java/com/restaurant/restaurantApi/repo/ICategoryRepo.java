package com.restaurant.restaurantApi.repo;
import com.restaurant.restaurantApi.model.Category;
import org.springframework.data.repository.CrudRepository;


public interface ICategoryRepo extends CrudRepository<Category, Integer>{
    Category findByCategory(Integer category);
}
