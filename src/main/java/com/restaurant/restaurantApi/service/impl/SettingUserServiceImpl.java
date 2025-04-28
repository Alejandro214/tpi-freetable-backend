package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.SettingUser;
import com.restaurant.restaurantApi.repo.ISettingUserRepo;
import com.restaurant.restaurantApi.service.inter.ISettingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingUserServiceImpl implements ISettingUserService {

    @Autowired
    private ISettingUserRepo settingUserRepo;


    @Override
    public SettingUser saveSettingUser(SettingUser settingUser) {
        return settingUserRepo.save(settingUser);
    }

    @Override
    public SettingUser findByUsername(String username) {
        return settingUserRepo.findByNombreUsuario(username);
    }
}
