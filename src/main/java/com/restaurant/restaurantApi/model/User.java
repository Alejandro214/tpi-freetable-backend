package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "table_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private Integer idUser;

    private String userName;

    private String email;

    private String password;

    private String rol = "user";

}
