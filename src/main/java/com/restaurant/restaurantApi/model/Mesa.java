package com.restaurant.restaurantApi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMesa;

    @OneToMany(mappedBy = "mesa")
    private List<Order> listPedidos;



    public void addOrder(Order order){
        this.listPedidos.add(order);
    }
}
