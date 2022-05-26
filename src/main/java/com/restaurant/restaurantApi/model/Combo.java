package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCombo")
    private Integer idCombo;

    private Double priceCombo;

    private String image;

    private String description;

    private String name;

}
