package com.restaurant.restaurantApi.model;

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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;
    private Integer category;
    private String  nameCategory;


    @ManyToMany(mappedBy = "listCategory",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return idCategory != null && Objects.equals(idCategory, category.idCategory);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
