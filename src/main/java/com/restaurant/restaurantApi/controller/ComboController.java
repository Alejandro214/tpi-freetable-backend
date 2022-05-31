package com.restaurant.restaurantApi.controller;


import com.restaurant.restaurantApi.common.ExceptionMessage;
import com.restaurant.restaurantApi.exception.SaveProductBadResquestException;
import com.restaurant.restaurantApi.model.Combo;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.inter.IComboService;
import com.restaurant.restaurantApi.service.inter.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("combo")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComboController {

    @Autowired
    private IComboService iComboService;

    @ApiOperation(value = "Create a new combo",notes = "Retorna el combo que se creo y guardo en la base")
    @PostMapping("saveCombo")
    public ResponseEntity<Combo> saveCombo(@RequestBody Combo combo){
        return new ResponseEntity<>(this.iComboService.save(combo),HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna todos los combos actuales",notes = "Retorna el producto que se creo y guardo en la base")
    @GetMapping("getAllCombos")
    public ResponseEntity<List<Combo>> getAllCombos(){
        return new ResponseEntity<>(this.iComboService.getAllCombos(),HttpStatus.OK);
    }


}
