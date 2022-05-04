package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import com.restaurant.restaurantApi.service.inter.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepo iOrderRepo;

    @Autowired
    private IProductRepo iProductRepo;

    @Override
    public Order saveOrder(Order order) {
        Product product = this.iProductRepo.findByName(order.getProducts().get(0).getName());
        order.setProducts(new ArrayList<>());
        order.addProduct(product);
        order.setTotalPrice(new Double(0));
        return this.iOrderRepo.save(order);
    }
}
