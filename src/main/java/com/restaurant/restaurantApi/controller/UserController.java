package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.security.entity.Usuario;
import com.restaurant.restaurantApi.security.service.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("userByUsername/{username}")
    public ResponseEntity<Usuario> findUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(this.usuarioService.getByNombreUsuario(username).get(), HttpStatus.OK);
    }
}
