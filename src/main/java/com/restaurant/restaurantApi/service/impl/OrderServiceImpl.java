package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.mapper.IProductMapper;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import com.restaurant.restaurantApi.service.inter.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepo iOrderRepo;

    @Autowired
    private IProductMapper iProductMapper;

    @Override
    public Order saveOrder(Order order) {
        List<Product> productList = order.getProducts();
        order.setProducts(new ArrayList<>());
        List<ProductResponse> responseList = this.iProductMapper.listProductsToListProductResponse(productList);
        List<Product> products    = iProductMapper.listProductsResponseToListProduct(responseList);
        order.addAllProducts(products);
        return this.iOrderRepo.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) this.iOrderRepo.findAll();
    }



}
