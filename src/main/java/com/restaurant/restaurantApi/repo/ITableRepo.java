package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Mesa;
import org.springframework.data.repository.CrudRepository;

public interface ITableRepo extends CrudRepository<Mesa, Integer> {


}
