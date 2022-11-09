package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrderService {

     Order saveOrder(Order order);

     List<Order> getAllOrdersPagados(Integer idMesa,String from, String to);

     void deleteOrder(Integer idOrder);

     Order getOrderById(Integer idOrder);

     Order getPedidoConfirmadoByIdMesa(Integer idMesa);

     Boolean existsByMesaAndStatusOrder(Mesa mesa, String statusOrder);

     Order findOrderByMesaAndStatusOrder(Mesa mesa, String statusOrder);

     Order pagarPedidoByMesaIdMesa(Integer idMesa);

     void deleteOrder(Order order);

     Order updateOrder(Mesa mesa, Order order);

}
