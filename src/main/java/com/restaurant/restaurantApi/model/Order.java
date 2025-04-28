package com.restaurant.restaurantApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@jakarta.persistence.Table(name = "table_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrder")
    private Integer idOrder;

    @ManyToMany(mappedBy = "listPedidos",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
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
