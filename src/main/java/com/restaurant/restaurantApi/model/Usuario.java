package com.restaurant.restaurantApi.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String nombre;
    @NotNull
    @Column(unique = true,name = "nombre_usuario")
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Rol rol;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="imageData_idImage", referencedColumnName = "idImage")
    private ImageData imageData;

    public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public Usuario() {
    }
}
