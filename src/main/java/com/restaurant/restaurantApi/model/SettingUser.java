package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
