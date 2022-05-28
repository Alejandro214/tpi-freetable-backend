package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.model.*;
import com.restaurant.restaurantApi.service.inter.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MenuController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IMesaService iMesaService;

    @Autowired
    private IComboService comboService;



    @ApiOperation(value = "Retorna la categoria que pertence a ese numero",notes = "Retorna la categoria perteneciente a ese numero" )
    @GetMapping("getCategoryByCategory/{category}")
    public ResponseEntity<Category> getCategoryByCategory(@PathVariable("category") Integer category){
        return new ResponseEntity<>(this.iCategoryService.findByCategory(category),HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna la mesa guardada")
    @PostMapping("saveMesa")
    public ResponseEntity<Mesa> saveMesa(@RequestBody Mesa mesa){
        return new ResponseEntity<>(this.iMesaService.saveMesa(mesa),HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna la mesa con el idMesa")
    @GetMapping("getMesaById/{idMesa}")
    public  ResponseEntity<Mesa> getMesaById(@PathVariable("idMesa") Integer idMesa){
        return new ResponseEntity<>(this.iMesaService.getMesaById(idMesa),HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna una lista de mesas")
    @GetMapping("findAllMesas")
    public ResponseEntity<List<Mesa>> findAllMesa(){
        return new ResponseEntity<>(this.iMesaService.findAllMesas(),HttpStatus.OK);
    }


    @ApiOperation(value = "Retorna todos lo combos del restaurante")
    @GetMapping("getAllCombos")
    public ResponseEntity<List<Combo>> getAllCombos(){
        List<Combo> combos = comboService.getAllCombos();
        return new ResponseEntity<>(combos,HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna la mesa a la que se le agrego el pedido")
    @PutMapping("addOrderMesa/{idMesa}")
    public ResponseEntity<Mesa> addOrderByIdMesa(@PathVariable("idMesa") Integer idMesa,@RequestBody Order order){
        Mesa mesa = this.iMesaService.addOrderByIdMesa(idMesa,order);
        return new ResponseEntity<>(mesa,HttpStatus.OK);
    }



}
