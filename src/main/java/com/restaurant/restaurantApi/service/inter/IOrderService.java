package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Order;

import java.util.List;

public interface IOrderService {

    public Order saveOrder(Order order);

    public List<Order> getAllOrders();
}
