package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.UsuarioService;
import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.model.Rol;
import com.restaurant.restaurantApi.model.Usuario;
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
    UsuarioService usuarioService;

    private Usuario usuario;


    @BeforeEach
    public void set_up(){
        Rol rol = new Rol(RolNombre.ROLE_ADMIN);
        usuario = new Usuario();
        usuario.setNombre("user");
        usuario.setNombreUsuario("user123");
        usuario.setPassword("123");
        usuario.setEmail("user@gmail.com");
        usuario.setRol(rol);
        this.usuarioService.save(usuario);
    }

    @Test
    public void guardar_usuario(){
        Rol rol = new Rol(RolNombre.ROLE_ADMIN);
        Usuario user = new Usuario();
        user = new Usuario();
        user.setNombre("luigi");
        user.setNombreUsuario("luigi123");
        user.setPassword("123");
        user.setEmail("luigi@gmail.com");
        user.setRol(rol);
        Usuario usuarioGuardado = this.usuarioService.save(user);
        assertNotNull(usuarioGuardado.getId());
        assertEquals(usuarioGuardado.getNombre(),"luigi");
        assertEquals(usuarioGuardado.getNombreUsuario(),"luigi123");
        assertEquals(usuarioGuardado.getPassword(),"123");
        assertEquals(usuarioGuardado.getEmail(),"luigi@gmail.com");
        assertEquals(usuarioGuardado.getRol().getRolNombre(),RolNombre.ROLE_ADMIN);
    }
    @Test
    public void existe_mail(){
        Boolean existsEmail = this.usuarioService.existsByEmail("user@gmail.com");
        assertTrue(existsEmail);
    }

    @Test
    public void existe_nombre_usuario(){
        Boolean existsNombreUsuario = this.usuarioService.existsByNombreUsuario("user123");
        assertTrue(existsNombreUsuario);
    }

    @Test
    public void obtener_usuario_por_nombre_de_usuario(){
        Usuario usuario = this.usuarioService.getByNombreUsuario("user123").get();
        assertNotNull(usuario.getId());
        assertEquals(usuario.getNombre(),"user");
        assertEquals(usuario.getNombreUsuario(),"user123");
        assertEquals(usuario.getEmail(),"user@gmail.com");
        assertEquals(usuario.getPassword(),"123");
        assertEquals(usuario.getRol().getRolNombre(),RolNombre.ROLE_ADMIN);
    }

}
