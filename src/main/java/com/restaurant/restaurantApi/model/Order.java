package com.restaurant.restaurantApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Data
@Entity
@Table(name = "table_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrder")
    private Integer idOrder;

    @ManyToMany(mappedBy = "listPedidos",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @JoinColumn(name="idMesa",referencedColumnName = "idMesa")
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Mesa mesa;




    public void addProduct(Product product){
        this.products.add(product);
        product.addPedido(this);
    }

    public void addAllProducts(List<Product> productList){
        for(Product p: productList){
            p.addPedido(this);
        }
        this.products.addAll(productList);
    }
}
