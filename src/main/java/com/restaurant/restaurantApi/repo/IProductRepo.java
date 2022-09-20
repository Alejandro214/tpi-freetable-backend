package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;


public interface IProductRepo extends CrudRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

    @Query(value = " SELECT cantProduct FROM restaurant_db.table_order o " +
            " INNER JOIN productos_pedidos pp ON   pp.id_order = o.idOrder  " +
            " where idOrder = :idOrder and idMesa = :idMesa and id_product = :idProduct ", nativeQuery = true)
    Integer getCantProductByIdMesaAndIdOrder(Integer idProduct, Integer idMesa, Integer idOrder);

    @Query(value = "SELECT p.idProduct,p.name,p.image,p.price,p.description,pp.cantProduct,0 AS clazz_ FROM restaurant_db.product p " +
            " INNER JOIN productos_pedidos pp on p.idProduct = pp.id_product " +
            " WHERE pp.id_order = :idOrder ",nativeQuery = true)
    List<Product> findAllProductsByIdOrder(Integer idOrder);
    Product findByName(String name);
}
