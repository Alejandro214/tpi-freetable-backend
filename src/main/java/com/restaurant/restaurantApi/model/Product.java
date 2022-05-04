package com.restaurant.restaurantApi.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private Integer idProduct;
    private String name;
    private String image;
    private Double price;
    private String  description;
    private Integer category;

    @JoinColumn(name="idOrder",referencedColumnName = "idOrder")
    @ManyToOne
    private Order order;
}
