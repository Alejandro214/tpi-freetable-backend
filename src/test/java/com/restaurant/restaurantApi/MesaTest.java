package com.restaurant.restaurantApi;

import com.restaurant.restaurantApi.service.inter.IMesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MesaTest {

    @Autowired
    private IMesaService mesaService;
}
