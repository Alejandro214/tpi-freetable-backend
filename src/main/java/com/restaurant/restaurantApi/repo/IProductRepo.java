package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IProductRepo extends CrudRepository<Product, Integer> {
    Product findByName(String name);


    @Query(value = "Select   p.idProduct,p.name,p.image,p.price,p.description,p.cantProduct,0 AS clazz_ from product AS p  " +
            " join productos_category as pg on pg.product = p.idProduct " +
            " join category as g on pg.category = g.category " +
            " where name LIKE %:name% xor g.nameCategory LIKE %:name% ",nativeQuery = true)
    Page<Product> filterProductByName(Pageable pageable, String name);

    @Modifying
    @Transactional
    @Query(value = " DELETE FROM restaurant_db.productos_pedidos " +
            " WHERE product = :idProduct and table_order = :idOrder ",nativeQuery = true)
    void deleteProductDeOrder(Integer idProduct,Integer idOrder);

    @Query(value = "SELECT p.idProduct,p.name,p.image,p.price,p.description,p.cantProduct,0 AS clazz_ FROM Product as p ", nativeQuery = true)
    List<Product> findAllProducts();

}
