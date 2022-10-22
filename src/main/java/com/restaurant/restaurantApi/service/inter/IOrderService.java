package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrderService {

     Order saveOrder(Order order);

     List<Order> getAllOrders(Integer idMesa,String statusOrder);

     void deleteOrder(Integer idOrder);

     Order getOrderById(Integer idOrder);

     Order getOrderConfirmado(Integer idMesa);

     Boolean existsByMesaAndStatusOrder(Mesa mesa, String statusOrder);

     Order findOrderByMesaAndStatusOrder(Mesa mesa, String statusOrder);

}
