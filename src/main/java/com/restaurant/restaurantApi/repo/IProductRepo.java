package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends CrudRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

    @Query(value = " SELECT cantProduct FROM table_order o " +
            " INNER JOIN productos_pedidos pp ON   pp.id_order = o.idOrder  " +
            " where idOrder = :idOrder and idMesa = :idMesa and id_product = :idProduct ", nativeQuery = true)
    Integer getCantProductByIdMesaAndIdOrder(Integer idProduct, Integer idMesa, Integer idOrder);

    @Query(value = "SELECT p.idProduct,p.name,p.image,p.price,pp.cantProduct,0 AS clazz_ FROM product p " +
            " INNER JOIN productos_pedidos pp on p.idProduct = pp.id_product " +
            " WHERE pp.id_order = :idOrder ",nativeQuery = true)
    List<Product> findAllProductsByIdOrder(Integer idOrder);

    Product findByName(String name);


    @Query(value = "SELECT * FROM product p " +
            "   WHERE p.name LIKE %:nameProduct% " +
            "   LIMIT 5 ",nativeQuery = true)
    List<Product> findAllProducts(String nameProduct);

    @Query(value="  SELECT pp.cantProduct FROM productos_pedidos pp " +
                 "   WHERE pp.id_order = :idOrder and pp.id_product =:idProduct ",nativeQuery = true)
    Integer getCantProductByIdMesaAndIdProduct(Integer idProduct, Integer idOrder);
}
