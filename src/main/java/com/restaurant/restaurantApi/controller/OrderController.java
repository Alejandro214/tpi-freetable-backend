package com.restaurant.restaurantApi.controller;


import com.restaurant.restaurantApi.common.ExceptionMessage;
import com.restaurant.restaurantApi.exception.DeleteOrderException;
import com.restaurant.restaurantApi.exception.GetAllOrdersByIdMesaException;
import com.restaurant.restaurantApi.exception.SaveOrderBadRequestException;
import com.restaurant.restaurantApi.exception.UpdateOrderException;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        try {
            return new ResponseEntity<>(this.iOrderService.saveOrder(order), HttpStatus.OK);
        }catch (Exception e){
            throw new SaveOrderBadRequestException(ExceptionMessage.SAVE_ORDER_ERROR.getValue());
        }
    }
    @ApiOperation(value = "Retorna los todos pedidos",notes = "Dado un idMesa, retorna todos los pedidos que se realizaron en la mesa con dicho idMesa")
    @GetMapping("getAllOrders/{idMesa}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable("idMesa") Integer idMesa){
        try {
            return new ResponseEntity<>(this.iOrderService.getAllOrders(idMesa), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new GetAllOrdersByIdMesaException(ExceptionMessage.GER_ALL_ORDERS_BY_MESA_EXCEPTION_.getValue());
        }
    }

    @ApiOperation(value= "Dado un idOrder, elimina el pedido con dicho idOrder")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SOPORTE')")
    @DeleteMapping("deleteOrder/{idOrder}")
    public ResponseEntity<String> deleteOrder(@PathVariable("idOrder") Integer idOrder){
        try {
            this.iOrderService.deleteOrder(idOrder);
            return new ResponseEntity<>("Se a eliminado el pedido", HttpStatus.OK);
        }catch (Exception e){
            throw new DeleteOrderException(ExceptionMessage.DELETE_ORDER_EXCEPTION.getValue());

        }
    }

    @ApiOperation(value = "Dado un pedido, lo actualiza con los nuevos campos cambiado, y retorna el pedido actualizado")
    @PutMapping("updateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        try{
            return new ResponseEntity<>(this.iOrderService.updateOrder(order),HttpStatus.OK);
        }catch (Exception e){
             throw new UpdateOrderException(ExceptionMessage.UPDATE_ORDER_EXCEPTION.getValue());
        }
    }

    @ApiOperation("Dado un idProduct, idOrder y una cant, agrega el producto al pedido con idProduct y lo retorna")
    @PostMapping("addProductOrder/{idProduct}/{idOrder}/{cant}")
    public ResponseEntity<Order> addProductOrder(@PathVariable("idProduct") Integer idProduct,
                                                  @PathVariable("idOrder") Integer idOrder,
                                                  @PathVariable("cant") Integer cant){
        return new ResponseEntity(this.iOrderService.addProductOrder(idProduct,idOrder,cant),HttpStatus.OK);

    }
}
