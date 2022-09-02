package com.restaurant.restaurantApi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idProduct")
    private Integer idProduct;
    private String name;
    private String image;
    private Double price;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "productos_pedidos",
            joinColumns = @JoinColumn(name = "id_product",referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "id_order",referencedColumnName = "idOrder"))
    @JsonIgnore
    @ToString.Exclude
    private List<Order> listPedidos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "productos_category",
            joinColumns = @JoinColumn(name = "id_product",referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "id_category",referencedColumnName = "idCategory")
    )
    @JsonIgnore
    @ToString.Exclude
    private List<Category> listCategory;

    private Integer cantProduct = 1;

    public void addPedido(Order pedido){
        this.listPedidos.add(pedido);
    }

    public void incrementCantProduct() {
        this.cantProduct +=1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return idProduct != null && Objects.equals(idProduct, product.idProduct);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    public boolean containsOrder(Order order){
        System.out.println(this.getListPedidos());
        return this.getListPedidos().contains(order);
    }
}
