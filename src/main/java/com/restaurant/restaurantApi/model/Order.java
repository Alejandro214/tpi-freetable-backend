package com.restaurant.restaurantApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "table_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrder")
    private Integer idOrder;

    @ManyToMany(mappedBy = "listPedidos",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @Column(name = "totalPrice")
    @NotNull
    private Double totalPrice;

    @JoinColumn(name="idMesa",referencedColumnName = "idMesa")
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Mesa mesa;

    @NotBlank
    private String dateOrder;

    @NotBlank
    private String statusOrder;


    public void deleteMesa(){
        this.mesa = null;
    }


}
