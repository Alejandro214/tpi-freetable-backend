package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;
    @OneToMany
    private List<Product> products;

    public Double getTotalPriceProducts(){
        return this.products.stream().mapToDouble(p -> p.getPrice()).sum();
    }
}
