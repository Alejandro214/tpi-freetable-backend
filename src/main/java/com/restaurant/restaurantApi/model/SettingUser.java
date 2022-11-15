package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SettingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSettingUser;
    private String nombreUsuario;
    private Integer cantMesas = 10;
    private String imagenData;
}
