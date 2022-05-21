package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepo iOrderRepo;


    @Override
    public Order saveOrder(Order order) {
        this.updateProductosPedidos(order);
        List<Product> productList = order.getProducts();
        order.setProducts(new ArrayList<>());
        order.addAllProducts(productList);
        Order order1 = this.iOrderRepo.save(order);
        return order1;
    }


    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) this.iOrderRepo.findAll();
    }

    @Override
    public void deleteOrder(Integer idOrder) {
        Order order = this.iOrderRepo.findById(idOrder).get();
        order.setMesa(null);
        this.iOrderRepo.delete(order);
    }

    @Override
    public void updateProductosPedidos(Order order){
        for(Product p: order.getProducts()){
            this.iOrderRepo.updateProductosPedidos(p.getIdProduct(),order.getIdOrder());
        }
    }


}
