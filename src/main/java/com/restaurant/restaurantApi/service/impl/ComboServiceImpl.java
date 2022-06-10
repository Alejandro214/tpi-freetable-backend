package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Combo;
import com.restaurant.restaurantApi.repo.IComboRepo;
import com.restaurant.restaurantApi.service.inter.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboServiceImpl implements IComboService {
    @Autowired
    private IComboRepo iComboRepo;

    @Override
    public Combo save(Combo combo) {
        return this.iComboRepo.save(combo);
    }

    @Override
    public List<Combo> getAllCombos() {
        return  this.iComboRepo.findAllCombos();
    }

    @Override
    public List<Combo> findAllCombosByIdMesa(Integer idMesa) {
        return this.iComboRepo.findAllCombosByIdMesa(idMesa);
    }
}
