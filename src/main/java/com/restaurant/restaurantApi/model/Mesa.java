package com.restaurant.restaurantApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMesa;

    @OneToMany(mappedBy = "mesa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> listPedidos = new ArrayList<>();

    private String estadoMesa = "Disponible";

    @NotNull
    private Integer positionMesa;

    @NotNull
    private Integer numeroMesa;


    public void addOrder(Order order){
        this.listPedidos.add(order);
        order.setMesa(this);
    }

}
