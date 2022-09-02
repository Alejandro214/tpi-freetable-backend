package com.restaurant.restaurantApi.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class NuevoUsuario {
    @NotBlank
    @NotNull
    private String nombre;
    @NotBlank
    @NotNull
    private String nombreUsuario;
    @Email
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;

    private String rol = "ROLE_USER";


}
