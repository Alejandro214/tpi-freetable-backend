package com.restaurant.restaurantApi.security.controller;

import com.restaurant.restaurantApi.dto.Mensaje;
import com.restaurant.restaurantApi.security.dto.JwtDto;
import com.restaurant.restaurantApi.security.dto.LoginUsuario;
import com.restaurant.restaurantApi.security.dto.NuevoUsuario;
import com.restaurant.restaurantApi.security.entity.Rol;
import com.restaurant.restaurantApi.security.entity.Usuario;
import com.restaurant.restaurantApi.security.entity.UsuarioPrincipal;
import com.restaurant.restaurantApi.security.enums.RolNombre;
import com.restaurant.restaurantApi.security.jwt.JwtProvider;
import com.restaurant.restaurantApi.security.service.RolService;
import com.restaurant.restaurantApi.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Rol rol = rolService.getByRolNombre(RolNombre.ROLE_USER).get() ;
        if(nuevoUsuario.getRol().equals("ROLE_ADMIN")){
            rol = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
        }
        if(nuevoUsuario.getRol().equals(("ROLE_SOPORTE"))){
            rol = rolService.getByRolNombre(RolNombre.ROLE_SOPORTE).get();
        }
        usuario.setRol(rol);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, usuarioPrincipal.getUsername(), usuarioPrincipal.getRol().getRolNombre().toString());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
