package com.restaurant.restaurantApi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "mesa")
    @ToString.Exclude
    private List<Order> listPedidos = new ArrayList<>();

    private String estadoMesa = "Disponible";

    private Integer positionMesa;

    public void addOrder(Order order){
        this.listPedidos.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Mesa mesa = (Mesa) o;
        return idMesa != null && Objects.equals(idMesa, mesa.idMesa);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
