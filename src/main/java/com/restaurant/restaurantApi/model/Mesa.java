package com.restaurant.restaurantApi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMesa;

    @OneToMany(mappedBy = "mesa",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Order> listPedidos = new ArrayList<>();

    private String estadoMesa = "Disponible";

    @NotNull
    private Integer positionMesa;

    @NotNull
    private Integer numeroMesa;


    public void addOrder(Order order){
        this.listPedidos.add(order);
    }

}
