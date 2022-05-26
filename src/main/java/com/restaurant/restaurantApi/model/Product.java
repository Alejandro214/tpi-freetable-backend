package com.restaurant.restaurantApi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "productos_pedidos",
            joinColumns = @JoinColumn(name = "product",referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "table_order",referencedColumnName = "idOrder"))
    @JsonIgnore
    private List<Order> listPedidos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "productos_category",
            joinColumns = @JoinColumn(name = "product",referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "category",referencedColumnName = "idCategory")
    )
    @JsonIgnore
    private List<Category> listCategory;

    public void addPedido(Order pedido){
        this.listPedidos.add(pedido);
    }

}
