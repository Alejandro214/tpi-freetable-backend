package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.SettingUser;

public interface ISettingUserService {
    SettingUser saveSettingUser(SettingUser settingUser);
    SettingUser findByUsername(String username);
}
