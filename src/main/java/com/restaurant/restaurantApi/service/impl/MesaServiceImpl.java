package com.restaurant.restaurantApi.service.impl;


import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.repo.IMesaRepo;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MesaServiceImpl implements IMesaService {

    @Autowired
    private IMesaRepo iMesaRepo;


    @Autowired
    private IProductRepo iProductRepo;

    @Autowired
    private IOrderRepo iOrderRepo;

    @Override
    public Mesa saveMesa(Mesa mesa) {
        List<Order> orderList = mesa.getListPedidos();
        mesa.setListPedidos(new ArrayList<>());
        Mesa mesa1 = this.iMesaRepo.save(mesa);
        this.agregarMesaAPedidos(orderList,mesa1);
        this.iOrderRepo.saveAll(orderList);
        mesa1.setListPedidos(orderList);
        return this.iMesaRepo.save(mesa1);
    }

    public void agregarMesaAPedidos(List<Order> orderList,Mesa mesa){
        for (Order order:orderList){
            order.setMesa(mesa);
        }
    }
}
