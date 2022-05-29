package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface IProductRepo extends CrudRepository<Product, Integer> {
    Product findByName(String name);


    @Query(value = "Select   p.idProduct,p.name,p.image,p.price,p.description from product AS p  " +
            " join productos_category as pg on pg.product = p.idProduct " +
            " join category as g on pg.category = g.category " +
            " where name LIKE %:name% xor g.nameCategory LIKE %:name% ",nativeQuery = true)
    Page<Product> filterProductByName(Pageable pageable, String name);

}
