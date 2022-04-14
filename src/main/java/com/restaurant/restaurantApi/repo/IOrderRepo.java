package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface IOrderRepo extends CrudRepository<Order, Integer> {
}
