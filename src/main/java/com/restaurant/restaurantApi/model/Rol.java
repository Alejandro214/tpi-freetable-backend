package com.restaurant.restaurantApi.model;

import com.restaurant.restaurantApi.enums.RolNombre;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rol_nombre")
    private RolNombre rolNombre;

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Rol() {
    }
}
