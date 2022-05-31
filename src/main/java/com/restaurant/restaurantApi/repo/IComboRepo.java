package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Combo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IComboRepo  extends CrudRepository<Combo, Integer> {
    @Query(value = "SELECT * FROM restaurant_db.combo ", nativeQuery = true)
    List<Combo> findAllCombos();

}
