package com.restaurant.restaurantApi.controller;


import com.restaurant.restaurantApi.common.ExceptionMessage;
import com.restaurant.restaurantApi.dto.Mensaje;
import com.restaurant.restaurantApi.exception.DeleteOrderException;
import com.restaurant.restaurantApi.exception.SaveOrderBadRequestException;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @ApiOperation(value = "Create a new order",notes = "Dado un order, lo guarda en la base")
    @PostMapping("saveOrder")
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Error, campos invalidos"), HttpStatus.BAD_REQUEST);
        try {
            return new ResponseEntity<>(this.iOrderService.saveOrder(order), HttpStatus.OK);
        }catch (Exception e){
            throw new SaveOrderBadRequestException(ExceptionMessage.SAVE_ORDER_ERROR.getValue());
        }
    }
    @ApiOperation(value = "Retorna los todos pedidos",notes = "Dado un idMesa, retorna todos los pedidos que se realizaron en la mesa con dicho idMesa")
    @GetMapping("getAllOrdersPagados/{idMesa}/{from}/{to}")
    public ResponseEntity<List<Order>> getAllOrdersPagados(@PathVariable("idMesa") Integer idMesa,@PathVariable(value = "from")String from,
                                                           @PathVariable(value = "to") String to){
            return new ResponseEntity<>(this.iOrderService.getAllOrdersPagados(idMesa,from,to), HttpStatus.OK);

    }

    @ApiOperation(value= "Dado un idOrder, elimina el pedido con dicho idOrder")
    @DeleteMapping("deleteOrder/{idOrder}")
    public ResponseEntity<String> deleteOrder(@PathVariable("idOrder") Integer idOrder){
        try {
            this.iOrderService.deleteOrder(idOrder);
            return new ResponseEntity<>("Se a eliminado el pedido", HttpStatus.OK);
        }catch (Exception e){
            throw new DeleteOrderException(ExceptionMessage.DELETE_ORDER_EXCEPTION.getValue());

        }
    }

    @ApiOperation(value = "Retorna el pedido confirmado que pertenece al idMesa",notes = "Dado un idMesa, retorna el pedido que pertenece a esa mesa con dicho idMesa")
    @GetMapping("getOrderConfirmado/{idMesa}")
    public ResponseEntity<Order> getOrderConfirmado(@PathVariable("idMesa") Integer idMesa){
            return new ResponseEntity<>(this.iOrderService.getPedidoConfirmadoByIdMesa(idMesa), HttpStatus.OK);
    }

    @PutMapping("pagarPedidoByMesaIdMesa/{idMesa}")
    public ResponseEntity<Order> pagarPedidoByMesaIdMesa(@PathVariable("idMesa") Integer idMesa){
        return new ResponseEntity<>(this.iOrderService.pagarPedidoByMesaIdMesa(idMesa), HttpStatus.OK);
    }




}
