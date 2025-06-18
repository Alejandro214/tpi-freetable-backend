package com.restaurant.restaurantApi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Order> listPedidos = new ArrayList<>();

    public void addPedido(Order order) {
        if (!this.listPedidos.contains(order)) {
            this.listPedidos.add(order);
            order.getProducts().add(this);
        }
    }

    public void removePedido(Order order) {
        if (this.listPedidos.contains(order)) {
            this.listPedidos.remove(order);
            order.getProducts().remove(this);
        }
    }
}
