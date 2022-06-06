package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;

import java.util.List;

public interface IMesaService {
    Mesa saveMesa(Mesa mesa);
    Mesa getMesaById(Integer idMesa);
    List<Mesa> findAllMesas();
    Mesa addOrderByIdMesa(Integer idMesa, Order order);
    Mesa changeEstadoMesa(Integer idMesa,String newEstadoMesa);
}
