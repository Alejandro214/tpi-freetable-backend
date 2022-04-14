package com.restaurant.restaurantApi.service;

import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private IOrderRepo iOrderRepo;

    @Override
    public Order saveOrder(Order order) {
        return this.iOrderRepo.save(order);
    }
}
