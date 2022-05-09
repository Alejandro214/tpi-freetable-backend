package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Mesa;
import org.springframework.data.repository.CrudRepository;

public interface IMesaRepo extends CrudRepository<Mesa, Integer> {
}
