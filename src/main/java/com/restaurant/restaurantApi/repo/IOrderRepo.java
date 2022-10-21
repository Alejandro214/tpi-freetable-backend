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
    void updateProductosPedidos(Integer idProduct, Integer idPedido,Integer cantProduct);

    List<Order> findByMesaAndStatusOrder(Mesa mesa,String StatusOrder);


}
