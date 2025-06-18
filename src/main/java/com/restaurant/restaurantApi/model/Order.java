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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "productos_pedidos",
            joinColumns = @JoinColumn(name = "id_order", referencedColumnName = "idOrder"),
            inverseJoinColumns = @JoinColumn(name = "id_product", referencedColumnName = "idProduct")
    )
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();




    @Column(name = "totalPrice")
    @NotNull
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMesa")
    private Mesa mesa;

    @NotBlank
    private String dateOrder;

    @NotBlank
    private String statusOrder;


    public void deleteMesa(){
        this.mesa = null;
    }


    public void addProduct(Product product) {
        this.products.add(product);
        product.getListPedidos().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getListPedidos().remove(this);
    }


}
