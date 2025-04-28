package com.restaurant.restaurantApi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
public class SettingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSettingUser;
    @NotBlank
    @NotNull
    private String nombreUsuario;
    private Integer cantMesas = 10;
    private String imagenData;
}
