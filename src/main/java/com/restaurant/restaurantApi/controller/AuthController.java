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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private RolService rolService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new Mensaje("Error, campos inválidos"));
        }
        if (usuarioServiceImpl.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return ResponseEntity.badRequest().body(new Mensaje("Error, ese username ya existe"));
        }
        if (usuarioServiceImpl.existsByEmail(nuevoUsuario.getEmail())) {
            return ResponseEntity.badRequest().body(new Mensaje("Error, ese email ya existe"));
        }

        User user = new User(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword())
        );

        // Asignar rol con manejo seguro
        Rol rol = rolService.getByRolNombre(RolNombre.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Rol ROLE_USER no encontrado"));

        String rolSolicitado = nuevoUsuario.getRol();
        if (rolSolicitado != null) {
            switch (rolSolicitado) {
                case "ROLE_ADMIN":
                    rol = rolService.getByRolNombre(RolNombre.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Rol ROLE_ADMIN no encontrado"));
                    break;
                case "ROLE_SOPORTE":
                    rol = rolService.getByRolNombre(RolNombre.ROLE_SOPORTE)
                            .orElseThrow(() -> new RuntimeException("Rol ROLE_SOPORTE no encontrado"));
                    break;
                // Puedes agregar más roles aquí si hace falta
            }
        }
        user.setRol(rol);
        usuarioServiceImpl.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario guardado"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new Mensaje("Error, campos inválidos"));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        PrimaryUser primaryUser = (PrimaryUser) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, primaryUser.getUsername(), primaryUser.getRol().getRolNombre().toString());
        return ResponseEntity.ok(jwtDto);
    }

}
