package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.SettingUser;
import com.restaurant.restaurantApi.model.User;
import com.restaurant.restaurantApi.repo.UserRepository;
import com.restaurant.restaurantApi.service.inter.ISettingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ISettingUserService iSettingUserService;

    public Optional<User> getByNombreUsuario(String nombreUsuario){
        return userRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return userRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User save(User user){
        User user1 = userRepository.save(user);
        SettingUser settingUser = new SettingUser();
        settingUser.setNombreUsuario(user.getNombreUsuario());
        settingUser.setCantMesas(10);
        this.iSettingUserService.saveSettingUser(settingUser);
        return user1;
    }

}
