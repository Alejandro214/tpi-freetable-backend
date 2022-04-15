package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.IOrderService;
import com.restaurant.restaurantApi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IProductService iProductService;

    @PostMapping("saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        return new ResponseEntity<Order>(this.iOrderService.saveOrder(order), HttpStatus.OK);
    }

    @PostMapping("saveProduct")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody Product product){
        return new ResponseEntity<ProductResponse>(this.iProductService.saveProduct(product),HttpStatus.OK);
    }
}
