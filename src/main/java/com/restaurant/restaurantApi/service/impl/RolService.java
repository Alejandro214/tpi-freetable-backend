package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Rol;
import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.repo.RolRepository;
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

    public Rol save(Rol rol){
       return  this.rolRepository.save(rol);
    }


}
