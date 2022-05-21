package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Order;

import java.util.List;

public interface IOrderService {

     Order saveOrder(Order order);

     List<Order> getAllOrders(Integer idMesa);

     void deleteOrder(Integer idOrder);

     void updateProductosPedidos(Order order);
}
