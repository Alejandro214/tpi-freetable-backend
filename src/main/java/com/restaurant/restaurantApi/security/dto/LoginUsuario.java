package com.restaurant.restaurantApi.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginUsuario {

    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;
}
