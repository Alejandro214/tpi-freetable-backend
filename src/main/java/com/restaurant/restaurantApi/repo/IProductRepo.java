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
            " INNER JOIN productos_pedidos pp ON   pp.id_order = o.idOrder  " +
            " where idOrder = :idOrder and idMesa = :idMesa and id_product = :idProduct ", nativeQuery = true)
    Integer getCantProductByIdMesaAndIdOrder(Integer idProduct, Integer idMesa, Integer idOrder);

    @Transactional
    @Modifying
    @Query(value = " UPDATE productos_pedidos SET id_product = :idProductACambiar ,cantProduct = 1 " +
            " WHERE (id_product = :idProductAReemplazar) and (id_order = :idOrder) ",nativeQuery = true)
    void reemplazarProductOrder(Integer idProductAReemplazar, Integer idOrder, Integer idProductACambiar);


    @Query(value = " SELECT ((SELECT COUNT(*) FROM productos_pedidos where id_product = :idProductACambiar and `id_order` = :idOrder ) > 0) ",nativeQuery = true)
    BigInteger existeProductoInPedidosProductos(Integer idOrder, Integer idProductACambiar);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `restaurant_db`.`productos_pedidos` SET `cantProduct` = cantProduct + 1 " +
            "WHERE (`id_product` = :idProduct) and (`id_order` = :idOrder) ",nativeQuery = true)
    void incrementarCantidadProductByIdProductAndIdOrder(Integer idProduct,Integer idOrder);

    @Query(value = "SELECT p.idProduct,p.name,p.image,p.price,p.description,pp.cantProduct,0 AS clazz_ FROM restaurant_db.product p " +
            " INNER JOIN productos_pedidos pp on p.idProduct = pp.id_product " +
            " LEFT JOIN combo c on c.idProduct = pp.id_product " +
            " WHERE pp.id_order = :idOrder ",nativeQuery = true)
    List<Product> findAllProductsByIdOrder(Integer idOrder);
}
