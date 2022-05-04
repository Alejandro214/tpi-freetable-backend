package com.restaurant.restaurantApi.model;

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

    @OneToMany(mappedBy = "order")
    private List<Product> products;

    @Column(name = "totalPrice")
    private Double totalPrice;


    public Double getTotalPriceProducts(){
        return this.products.stream().mapToDouble(p -> p.getPrice()).sum();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}
