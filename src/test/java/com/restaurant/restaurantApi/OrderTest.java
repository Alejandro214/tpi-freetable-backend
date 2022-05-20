package com.restaurant.restaurantApi;

import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderTest {


    @Autowired
    private IOrderService orderService;

    private Order order = new Order();


    @Test
    void save_order(){

    }

    @Test
    void get_find_all_orders(){

    }

}
