package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IProductRepo extends CrudRepository<Product, Integer> {
    Product findByName(String name);

    @Query(value = "Select * from product p " +
            "where p.name LIKE %:name% ", nativeQuery = true)
    Page<Product> filterProductByName(Pageable pageable, String name);

    List<Product> findAllByCategory(Integer category);
}
