package com.restaurant.restaurantApi.controller;


import com.restaurant.restaurantApi.common.ExceptionMessage;
import com.restaurant.restaurantApi.exception.SaveOrderBadRequestException;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @ApiOperation(value = "Create a new order",notes = "Retorna el pedido que se creo y guardo en la base")
    @PostMapping("saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        try {
            return new ResponseEntity<>(this.iOrderService.saveOrder(order), HttpStatus.OK);
        }catch (Exception e){
            throw new SaveOrderBadRequestException(ExceptionMessage.INCORRECT_ACCOUNT_INFO.getValue());

        }
    }
    @ApiOperation(value = "Retorna los todos pedidos",notes = "Retorna todos los pedidos que se realizaron hasta el momento")
    @GetMapping("getAllOrders/{idMesa}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable("idMesa") Integer idMesa){
        return new ResponseEntity<>(this.iOrderService.getAllOrders(idMesa),HttpStatus.OK);
    }

    @ApiOperation(value= "Elimina la orden")
    @DeleteMapping("deleteOrder/{idOrder}")
    public ResponseEntity<String> deleteOrder(@PathVariable("idOrder") Integer idOrder){
        this.iOrderService.deleteOrder(idOrder);;
        return new ResponseEntity<>("Se a eliminado el pedido",HttpStatus.OK);
    }
}
