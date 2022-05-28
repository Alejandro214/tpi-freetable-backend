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
    @Query(value = "INSERT INTO restaurant_db.productos_pedidos(product, table_order) VALUES (:idProduct, :idPedido)"
        , nativeQuery = true)
    void updateProductosPedidos(Integer idProduct, Integer idPedido);

    @Modifying
    @Transactional
    @Query(value = " DELETE FROM productos_pedidos " +
            " WHERE table_order = :idOrder ",nativeQuery = true)
    void deleteOrderProductosPedidos(Integer idOrder);

    List<Order> findAllByMesa(Mesa idMesa);
}
