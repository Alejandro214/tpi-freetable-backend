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


    //@Query(value = "Select   p.idProduct,p.name,p.image,p.price,p.description,p.cantProduct,0 AS clazz_ from product  p  " +
    //        " inner join productos_category as pg on pg.product = p.idProduct " +
     //       " inner join category as g on pg.category = g.category " +
     //       " where name LIKE %:name% xor g.nameCategory LIKE %:name% ",nativeQuery = true)
    Page<Product> findAllByName(Pageable pageable, String name);

    @Modifying
    @Transactional
    @Query(value = " DELETE FROM restaurant_db.productos_pedidos " +
            " WHERE product = :idProduct and table_order = :idOrder ",nativeQuery = true)
    void deleteProductDeOrder(Integer idProduct,Integer idOrder);

    //@Query(value = "SELECT * FROM Product  ", nativeQuery = true)
    Page<Product> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `restaurant_db`.`product` SET `cantProduct` = :newCant WHERE (`idProduct` = :idProduct)", nativeQuery = true)
    void updateCantProductById(Integer idProduct,Integer newCant);

    @Query(value = " SELECT cantProduct FROM restaurant_db.table_order o " +
            " INNER JOIN productos_pedidos pp ON   pp.table_order = o.idOrder  " +
            " where idOrder = :idOrder and idMesa = :idMesa and product = :idProduct ", nativeQuery = true)
    Integer getCantProductByIdMesaAndIdOrder(Integer idProduct, Integer idMesa, Integer idOrder);

    @Transactional
    @Modifying
    @Query(value = " UPDATE `restaurant_db`.`productos_pedidos` SET `product` = :idProductACambiar " +
            "WHERE (`product` = :idProductAReemplazar) and (`table_order` = :idOrder);",nativeQuery = true)
    void reemplazarProductOrder(Integer idProductAReemplazar, Integer idOrder, Integer idProductACambiar);

}
