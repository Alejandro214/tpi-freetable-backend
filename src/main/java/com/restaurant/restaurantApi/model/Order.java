package com.restaurant.restaurantApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "table_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrder")
    private Integer idOrder;

    @ManyToMany(mappedBy = "listPedidos",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @Column(name = "totalPrice")
    private Double totalPrice;

    @JoinColumn(name="idMesa",referencedColumnName = "idMesa")
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Mesa mesa;

    private String dateOrder;


    public void deleteMesa(){
        this.mesa = null;
    }

    public void deleteProduct(Product product){
        this.products.remove(product);
        this.totalPrice -=product.getPrice();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }



}
