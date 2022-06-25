package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.User;
import com.restaurant.restaurantApi.repo.IUserRepo;
import com.restaurant.restaurantApi.service.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepo iUserRepo;


    @Override
    public User saveUser(User user) {
        User user1 = this.iUserRepo.findByEmail(user.getEmail());
        if(user1 == null) {
            return this.iUserRepo.save(user);
        }else {
            return null;
        }
    }

    @Override
    public User findUserById(Integer idUser) {
        return this.iUserRepo.findById(idUser).get();
    }

    @Override
    public void deleteUser(User user) {
        this.iUserRepo.delete(user);
    }

    @Override
    public User changeRolUserById(Integer idUser,String newRol) {
        User user = this.findUserById(idUser);
        user.setRol(newRol);
        return this.saveUser(user);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        User user = this.iUserRepo.findByEmailAndPassword(email,password);
        return user;
    }
}
