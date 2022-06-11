package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepo extends CrudRepository<User, Integer> {

    User findByEmailAndPassword(String email,String password);
}
