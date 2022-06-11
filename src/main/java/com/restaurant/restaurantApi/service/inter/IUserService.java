package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.User;

public interface IUserService {
    User saveUser(User user);
    User findUserById(Integer idUser);
    void deleteUser(User user);
    User changeRolUserById(Integer idUser,String newRol);
    User findUserByEmailAndPassword(String email,String password);
}
