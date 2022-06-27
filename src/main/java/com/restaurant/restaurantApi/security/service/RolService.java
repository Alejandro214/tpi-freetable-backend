package com.restaurant.restaurantApi.security.service;

import com.restaurant.restaurantApi.security.entity.Rol;
import com.restaurant.restaurantApi.security.enums.RolNombre;
import com.restaurant.restaurantApi.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
       return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        this.rolRepository.save(rol);
    }


}
