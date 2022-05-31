package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Combo;

import java.util.List;

public interface IComboService {
    Combo save(Combo combo);
    List<Combo> getAllCombos();
}
