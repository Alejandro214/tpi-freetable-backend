package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "table_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private Integer idUser;

    @NotBlank(message="El username de usuario no puede estar vacío")
    private String userName;

    @NotBlank(message="El email de usuario no puede estar vacío")
    @Email
    private String email;

    @NotBlank(message = "El password no puede estar vacio")
    private String password;

    private String rol = "user";

}
