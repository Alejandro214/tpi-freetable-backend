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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private Integer idProduct;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String image;
    @NotNull
    private Double price;
    private Integer cantProduct;

    @ManyToMany
    @JoinTable(
            name = "productos_pedidos",
            joinColumns = @JoinColumn(name = "id_product",referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "id_order",referencedColumnName = "idOrder"))
    @JsonIgnore
    @ToString.Exclude
    private List<Order> listPedidos = new ArrayList<>();





}
