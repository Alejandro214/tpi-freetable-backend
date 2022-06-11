package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.User;
import com.restaurant.restaurantApi.repo.IUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setIdUser(1);
        user.setRol("user");
        user.setUserName("Pepe");
        user.setEmail("pepe@hotmail.com");
        user.setPassword("12345");
    }

    @Test
    void save_User(){
        when(userRepo.save(user)).thenReturn(user);
        User userSave = this.userService.saveUser(this.user);
        assertEquals(userSave.getRol(),this.user.getRol());
        assertEquals(userSave.getPassword(),this.user.getPassword());
        assertEquals(userSave.getEmail(),this.user.getEmail());
        assertEquals(userSave.getUserName(),this.user.getUserName());
        assertNotNull(userSave.getIdUser());
    }

    @Test
    void find_User_By_Id(){
        when(userRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(user));
        User userSave = this.userService.findUserById(1);
        assertEquals(userSave.getRol(),this.user.getRol());
        assertEquals(userSave.getPassword(),this.user.getPassword());
        assertEquals(userSave.getEmail(),this.user.getEmail());
        assertEquals(userSave.getUserName(),this.user.getUserName());
        assertNotNull(userSave.getIdUser());
    }

    @Test
    void change_Rol_User_By_Id(){
       /* when(userRepo.findById(1)).thenReturn(Optional.ofNullable(user));
        User userSave = this.userService.changeRolUserById(1,"admin");
        assertEquals(userSave.getRol(),this.user.getRol());
        assertEquals(userSave.getPassword(),this.user.getPassword());
        assertEquals(userSave.getEmail(),this.user.getEmail());
        assertEquals(userSave.getUserName(),this.user.getUserName());
        assertNotNull(userSave.getIdUser());*/
    }


}
