package com.restaurant.restaurantApi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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
            joinColumns = @JoinColumn(name = "product",referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "table_order",referencedColumnName = "idOrder"))
    @JsonIgnore
    private List<Order> listPedidos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "productos_category",
            joinColumns = @JoinColumn(name = "product",referencedColumnName = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "category",referencedColumnName = "idCategory")
    )
    @JsonIgnore
    private List<Category> listCategory;

    private Integer cantProduct = 1;

    public void addPedido(Order pedido){
        this.listPedidos.add(pedido);
    }

    public void incrementCantProduct() {
        this.cantProduct +=1;
    }

}
