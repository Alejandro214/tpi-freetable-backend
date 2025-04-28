package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.model.Rol;
import com.restaurant.restaurantApi.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class AuthServiceTest {

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    private User user;


    @BeforeEach
    public void set_up(){
        Rol rol = new Rol(RolNombre.ROLE_ADMIN);
        user = new User();
        user.setNombre("user");
        user.setNombreUsuario("user123");
        user.setPassword("123");
        user.setEmail("user@gmail.com");
        user.setRol(rol);
        this.usuarioServiceImpl.save(user);
    }

    @Test
    public void guardar_usuario(){
        Rol rol = new Rol(RolNombre.ROLE_ADMIN);
        User user = new User();
        user = new User();
        user.setNombre("luigi");
        user.setNombreUsuario("luigi123");
        user.setPassword("123");
        user.setEmail("luigi@gmail.com");
        user.setRol(rol);
        User userGuardado = this.usuarioServiceImpl.save(user);
        assertNotNull(userGuardado.getId());
        assertEquals(userGuardado.getNombre(),"luigi");
        assertEquals(userGuardado.getNombreUsuario(),"luigi123");
        assertEquals(userGuardado.getPassword(),"123");
        assertEquals(userGuardado.getEmail(),"luigi@gmail.com");
        assertEquals(userGuardado.getRol().getRolNombre(),RolNombre.ROLE_ADMIN);
    }
    @Test
    public void existe_mail(){
        Boolean existsEmail = this.usuarioServiceImpl.existsByEmail("user@gmail.com");
        assertTrue(existsEmail);
    }

    @Test
    public void existe_nombre_usuario(){
        Boolean existsNombreUsuario = this.usuarioServiceImpl.existsByNombreUsuario("user123");
        assertTrue(existsNombreUsuario);
    }

    @Test
    public void obtener_usuario_por_nombre_de_usuario(){
        User user = this.usuarioServiceImpl.getByNombreUsuario("user123").get();
        assertNotNull(user.getId());
        assertEquals(user.getNombre(),"user");
        assertEquals(user.getNombreUsuario(),"user123");
        assertEquals(user.getEmail(),"user@gmail.com");
        assertEquals(user.getPassword(),"123");
        assertEquals(user.getRol().getRolNombre(),RolNombre.ROLE_ADMIN);
    }

}
