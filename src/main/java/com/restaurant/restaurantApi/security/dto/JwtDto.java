package com.restaurant.restaurantApi.security.dto;

import com.restaurant.restaurantApi.security.entity.Rol;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

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
