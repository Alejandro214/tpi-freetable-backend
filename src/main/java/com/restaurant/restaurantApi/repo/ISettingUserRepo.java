package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.SettingUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISettingUserRepo extends JpaRepository<SettingUser, Integer> {

    SettingUser findByNombreUsuario(String username);
}
