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
    private Double totalPrice;

    @JoinColumn(name="idMesa",referencedColumnName = "idMesa")
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Mesa mesa;

    private String dateOrder;


    public void deleteMesa(){
        this.mesa = null;
    }

    public void deleteProduct(Product product){
        this.products.remove(product);
        this.totalPrice -=product.getPrice();
    }

    public void addProduct(Product product){
        this.products.add(product);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return idOrder != null && Objects.equals(idOrder, order.idOrder);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
