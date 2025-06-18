package com.restaurant.restaurantApi.service.impl;


import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.repo.ITableRepo;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MesaServiceImpl implements IMesaService {

    @Autowired
    private ITableRepo iTableRepo;

    @Autowired
    private IOrderService orderService;

    @Override
    public Mesa saveMesa(Mesa mesa) {
        return this.iTableRepo.save(mesa);
    }

    @Override
    public Mesa getMesaById(Integer idMesa) {
        return this.iTableRepo.findById(idMesa).orElse(null);
    }

    @Override
    public List<Mesa> findAllMesas() {
        return (List<Mesa>) this.iTableRepo.findAll();
    }

    @Override
    public Mesa addOrderByIdMesa(Integer idMesa, Order order) {
        Mesa mesa = this.getMesaById(idMesa);
        this.orderService.updateOrder(mesa,order);
        return this.iTableRepo.save(mesa);
    }

    @Override
    public Mesa changeEstadoMesa(Integer idMesa, String newEstadoMesa) {
        Mesa mesa = this.getMesaById(idMesa);
        mesa.setEstadoMesa(newEstadoMesa);
        return this.saveMesa(mesa);
    }

    @Override
    public void deleteMesaById(Integer idMesa) {
        Mesa mesa = this.getMesaById(idMesa);
        this.iTableRepo.delete(mesa);
    }

    @Override
    public Mesa updatePositionMesa(Integer idMesa, Integer position) {
        Mesa mesa = this.getMesaById(idMesa);
        mesa.setPositionMesa(position);
        return this.saveMesa(mesa);
    }

    @Transactional
    @Override
    public Mesa juntarMesas(Integer idMesaUno, Integer idMesaDos) {
        Mesa mesa1 = this.getMesaById(idMesaUno);
        Mesa mesa2 = this.getMesaById(idMesaDos);

        // Crear la nueva mesa fusionada
        Mesa nuevaMesa = new Mesa();
        nuevaMesa.setPositionMesa(mesa1.getPositionMesa()); // puedes cambiar esto si hay lógica distinta
        nuevaMesa.setEstadoMesa("Disponible");
        nuevaMesa.setNumeroMesa(generarNumeroFusionado(mesa1, mesa2)); // lógica opcional
        this.saveMesa(nuevaMesa); // persistimos primero

        // Fusionar pedidos
        List<Order> pedidos = new ArrayList<>();
        pedidos.addAll(mesa1.getListPedidos());
        pedidos.addAll(mesa2.getListPedidos());

        for (Order pedido : pedidos) {
            nuevaMesa.addOrder(pedido); // actualiza ambos lados
            pedido.setMesa(nuevaMesa);
            this.orderService.saveOrder(pedido); // puede no ser necesario si cascade = ALL
        }

        // Eliminar las mesas viejas
        iTableRepo.delete(mesa1);
        iTableRepo.delete(mesa2);

        return nuevaMesa;
    }

    private Integer generarNumeroFusionado(Mesa m1, Mesa m2) {
        return Integer.parseInt(m1.getNumeroMesa() + "" + m2.getNumeroMesa()); // Ej: 3 y 4 → 34
    }


}
