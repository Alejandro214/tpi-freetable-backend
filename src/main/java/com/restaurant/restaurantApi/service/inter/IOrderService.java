package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;

import java.util.List;

public interface IOrderService {

     Order saveOrder(Order order);

     List<Order> getAllOrders(Integer idMesa,String statusOrder);

     void deleteOrder(Integer idOrder);

     Order getOrderById(Integer idOrder);

     List<Product> getOrderConfirmado(Integer idMesa);




}
