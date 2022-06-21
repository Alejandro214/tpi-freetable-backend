package com.restaurant.restaurantApi.service.impl;


import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.repo.IMesaRepo;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements IMesaService {

    @Autowired
    private IMesaRepo iMesaRepo;

    @Autowired
    private IOrderService orderService;

    @Override
    public Mesa saveMesa(Mesa mesa) {
        return this.iMesaRepo.save(mesa);
    }

    @Override
    public Mesa getMesaById(Integer idMesa) {
        return this.iMesaRepo.findById(idMesa).get();
    }

    @Override
    public List<Mesa> findAllMesas() {
        return (List<Mesa>) this.iMesaRepo.findAll();
    }

    @Override
    public Mesa addOrderByIdMesa(Integer idMesa, Order order) {
        Mesa mesa = this.getMesaById(idMesa);
        order.setMesa(mesa);
        Order order1 = this.orderService.saveOrder(order);
        mesa.addOrder(order1);
        return this.iMesaRepo.save(mesa);
    }

    @Override
    public Mesa changeEstadoMesa(Integer idMesa,String newEstadoMesa) {
        Mesa mesa = this.getMesaById(idMesa);
        mesa.setEstadoMesa(newEstadoMesa);
        return this.saveMesa(mesa);
    }

    @Override
    public void deleteMesaById(Integer idMesa) {
        this.iMesaRepo.deleteById(idMesa);
    }

    @Override
    public Mesa updatePositionMesa(Integer idMesa,Integer position) {
        Mesa mesa = this.getMesaById(idMesa);
        mesa.setPositionMesa(position);
        return this.saveMesa(mesa);
    }
}
