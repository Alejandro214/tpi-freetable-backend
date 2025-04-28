package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.dto.Mensaje;
import com.restaurant.restaurantApi.dto.JwtDto;
import com.restaurant.restaurantApi.dto.LoginUsuario;
import com.restaurant.restaurantApi.dto.NuevoUsuario;
import com.restaurant.restaurantApi.model.Rol;
import com.restaurant.restaurantApi.model.User;
import com.restaurant.restaurantApi.model.PrimaryUser;
import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.jwt.JwtProvider;
import com.restaurant.restaurantApi.service.inter.RolService;
import com.restaurant.restaurantApi.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Error, campos invalidos"), HttpStatus.BAD_REQUEST);
        if(usuarioServiceImpl.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Error, ese username ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioServiceImpl.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Error, ese email ya existe"), HttpStatus.BAD_REQUEST);
        User user =
                new User(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Rol rol = rolService.getByRolNombre(RolNombre.ROLE_USER).get() ;
        if(nuevoUsuario.getRol().equals("ROLE_ADMIN")){
            rol = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
        }
        if(nuevoUsuario.getRol().equals(("ROLE_SOPORTE"))){
            rol = rolService.getByRolNombre(RolNombre.ROLE_SOPORTE).get();
        }
        user.setRol(rol);
        usuarioServiceImpl.save(user);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Error, campos invalidos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        PrimaryUser primaryUser = (PrimaryUser) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, primaryUser.getUsername(), primaryUser.getRol().getRolNombre().toString());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
