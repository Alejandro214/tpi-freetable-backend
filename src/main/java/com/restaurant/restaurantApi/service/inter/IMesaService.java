package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Mesa;

import java.util.List;

public interface IMesaService {
    Mesa saveMesa(Mesa mesa);
    Mesa getMesaById(Integer idMesa);
    List<Mesa> findAllMesas();
}
