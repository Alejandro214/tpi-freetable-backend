package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IOrderService iOrderService;

    @PostMapping("saveOrder")
    public ResponseEntity<Order> savePedido(Order order){
        return new ResponseEntity<Order>(this.iOrderService.saveOrder(order), HttpStatus.OK);
    }
}
