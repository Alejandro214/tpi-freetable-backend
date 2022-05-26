package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;
    private Integer category;
    private String  nameCategory;


    @ManyToMany(mappedBy = "listCategory",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
