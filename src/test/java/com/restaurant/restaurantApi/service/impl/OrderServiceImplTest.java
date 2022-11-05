package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IMesaRepo;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import org.apache.catalina.startup.ListenerCreateRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    @Mock
    private IOrderRepo iOrderRepo;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private IMesaRepo mesaRepo;
    private Product pizza = new Product();
    private Product fanta = new Product();
    Order pedidoPizza = new Order();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pizza.setIdProduct(1);
        pizza.setName("Pizza");
        pizza.setImage("Una imagen");
        pizza.setPrice(500d);
        pizza.setListPedidos(new ArrayList<>());
        pedidoPizza.setTotalPrice(500d);
        pedidoPizza.setIdOrder(1);
        List<Product> products = new ArrayList<>();
        Order pedidoFanta = new Order();
        pedidoFanta.setIdOrder(2);
        pedidoFanta.setTotalPrice(200d);
        fanta.setIdProduct(2);
        fanta.setImage("https://superlago.com.ar/wp-content/uploads/2021/01/7790895000454.jpg");
        fanta.setName("Fanta");
        fanta.setPrice(200d);
    }

    @Test
    void save_Order() {
        when(iOrderRepo.save(any(Order.class))).thenReturn(pedidoPizza);
        Order order = this.orderService.saveOrder(this.pedidoPizza);
        assertEquals(order.getIdOrder(),1);
        assertEquals(order.getProducts(),new ArrayList<>());
        assertEquals(order.getTotalPrice(),500d);
    }


}