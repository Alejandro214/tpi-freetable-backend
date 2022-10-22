package com.restaurant.restaurantApi.repo;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderRepo extends CrudRepository<Order, Integer> {
    @Modifying
    @Transactional
    @Query(value = " DELETE FROM productos_pedidos " +
            " WHERE id_order = :idOrder ",nativeQuery = true)
    void deleteOrderProductosPedidos(Integer idOrder);

    List<Order> findAllByMesaAndStatusOrder(Mesa idMesa,String statusOrder);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO restaurant_db.productos_pedidos(id_product, id_order,cantProduct) VALUES (:idProduct, :idPedido,:cantProduct)"
            , nativeQuery = true)
    void saveProductosPedidos(Integer idProduct, Integer idPedido,Integer cantProduct);


    @Modifying
    @Transactional
    @Query(value = " UPDATE restaurant_db.productos_pedidos SET `cantProduct` = :cantProduct WHERE (id_product = :idProduct) and (id_order = :idOrder)"
            , nativeQuery = true)
    void updateProductosPedidos(Integer cantProduct, Integer idProduct, Integer idOrder);

    @Query(value = " SELECT case when count(*) = 1 then 1 else 0 end as bool FROM restaurant_db.productos_pedidos pp " +
                  " WHERE pp.id_product = :idProduct  AND pp.id_order = :idOrder", nativeQuery = true)
    Integer existProductoPedido(Integer idOrder,Integer idProduct);


    Boolean existsByMesaAndStatusOrder(Mesa mesa,String statusOrder);

    Order findByMesaAndStatusOrder(Mesa mesa,String statusOrder);
}
