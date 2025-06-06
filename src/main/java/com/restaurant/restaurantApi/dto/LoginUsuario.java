package com.restaurant.restaurantApi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class LoginUsuario {

    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;
}
