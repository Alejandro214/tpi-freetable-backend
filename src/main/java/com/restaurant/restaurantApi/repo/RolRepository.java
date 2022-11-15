package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Rol;
import com.restaurant.restaurantApi.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
