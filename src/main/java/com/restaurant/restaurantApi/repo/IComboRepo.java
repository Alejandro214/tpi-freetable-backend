package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Combo;
import org.springframework.data.repository.CrudRepository;

public interface IComboRepo extends CrudRepository<Combo,Integer> {
}
