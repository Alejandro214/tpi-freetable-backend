package com.restaurant.restaurantApi.dto;

import lombok.Data;

@Data
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String nombreUsuario;
    private String rol;

    public JwtDto(String token, String nombreUsuario, String rol) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
    }

    public JwtDto() {
    }
}
