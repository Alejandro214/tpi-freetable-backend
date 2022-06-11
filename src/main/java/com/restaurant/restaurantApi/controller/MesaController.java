package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.common.ExceptionMessage;
import com.restaurant.restaurantApi.exception.*;
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
@RequestMapping("mesa")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MesaController {

    @Autowired
    private IMesaService iMesaService;


    @ApiOperation(value = "Guarda la mesa que recibe y retorna la misma ya guardada")
    @PostMapping("saveMesa")
    public ResponseEntity<Mesa> saveMesa(@RequestBody Mesa mesa){
        try {
            return new ResponseEntity<>(this.iMesaService.saveMesa(mesa), HttpStatus.OK);
        }catch (Exception e) {
            throw new SaveMesaException(ExceptionMessage.SAVE_MESA_EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "Retorna la mesa con el idMesa")
    @GetMapping("getMesaById/{idMesa}")
    public  ResponseEntity<Mesa> getMesaById(@PathVariable("idMesa") Integer idMesa){
        try {
            return new ResponseEntity<>(this.iMesaService.getMesaById(idMesa),HttpStatus.OK);
        }catch (Exception e) {
            throw new GetMesaByIdException(ExceptionMessage.GET_MESA_BY_ID_EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "Retorna una lista de mesas")
    @GetMapping("findAllMesas")
    public ResponseEntity<List<Mesa>> findAllMesa(){
        try {
            return new ResponseEntity<>(this.iMesaService.findAllMesas(),HttpStatus.OK);
        }catch (Exception e) {
            throw new FindAllMesaException(ExceptionMessage.FIND_ALL_MESAS_EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "Retorna la mesa a la que se le agrego el pedido")
    @PutMapping("addOrderMesa/{idMesa}")
    public ResponseEntity<Mesa> addOrderByIdMesa(@PathVariable("idMesa") Integer idMesa,@RequestBody Order order){
        try {
            Mesa mesa = this.iMesaService.addOrderByIdMesa(idMesa,order);
            return new ResponseEntity<>(mesa,HttpStatus.OK);
        }catch (Exception e) {
            throw new AddOrderByIdMesaException(ExceptionMessage.ADD_ORDER_BY_ID_MESA_EXCEPTION.getValue());
        }
    }
    @PutMapping("changeEstadoMensa/{idMesa}/{newEstadoMesa}")
    public ResponseEntity<Mesa> changeEstadoMesa(@PathVariable("idMesa") Integer idMesa,
                                                 @PathVariable("newEstadoMesa") String newEstadoMesa) {
        Mesa mesa = this.iMesaService.changeEstadoMesa(idMesa,newEstadoMesa);
        return new ResponseEntity<>(mesa,HttpStatus.OK);
    }








}
