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

    List<Order> findAllByMesa(Mesa idMesa);


}
