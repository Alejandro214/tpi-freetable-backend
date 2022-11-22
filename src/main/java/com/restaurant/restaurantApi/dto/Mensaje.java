package com.restaurant.restaurantApi.dto;

import lombok.Data;

@Data
public class Mensaje {
    private String mensaje;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
