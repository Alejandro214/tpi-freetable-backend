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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mesa")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MesaController {

    @Autowired
    private IMesaService iMesaService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Dada un mesa, la guarda en la base")
    @PostMapping("saveMesa")
    public ResponseEntity<Mesa> saveMesa(@RequestBody Mesa mesa){
        try {
            return new ResponseEntity<>(this.iMesaService.saveMesa(mesa), HttpStatus.OK);
        }catch (Exception e) {
            throw new SaveMesaException(ExceptionMessage.SAVE_MESA_EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "Dado un idMesa, retorna la mesa con dicho idMesa")
    @GetMapping("getMesaById/{idMesa}")
    public  ResponseEntity<Mesa> getMesaById(@PathVariable("idMesa") Integer idMesa){
        try {
            return new ResponseEntity<>(this.iMesaService.getMesaById(idMesa),HttpStatus.OK);
        }catch (Exception e) {
            throw new GetMesaByIdException(ExceptionMessage.GET_MESA_BY_ID_EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "Retorna todas las mesas actuales que tiene el restaurante")
    @GetMapping("findAllMesas")
    public ResponseEntity<List<Mesa>> findAllMesa(){
        try {
            return new ResponseEntity<>(this.iMesaService.findAllMesas(),HttpStatus.OK);
        }catch (Exception e) {
            throw new FindAllMesaException(ExceptionMessage.FIND_ALL_MESAS_EXCEPTION.getValue());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SOPORTE')")
    @ApiOperation(value = "Dado un idMesa y un pedido, agrega el pedido a la mesa con dicho idMesa y retorna la mesa con el pedido agregado")
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
    @ApiOperation("Dada un idMesa y un newEstadoMesa, actualiza el estado de esta mesa, y retorna la mesa con el estado actualizado")
    public ResponseEntity<Mesa> changeEstadoMesa(@PathVariable("idMesa") Integer idMesa,
                                                 @PathVariable("newEstadoMesa") String newEstadoMesa) {
        Mesa mesa = this.iMesaService.changeEstadoMesa(idMesa,newEstadoMesa);
        return new ResponseEntity<>(mesa,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("deleteMesaById/{idMesa}")
    @ApiOperation("Dado un idMesa, elimmina la mesa con dicho idMesa")
    public ResponseEntity<String> deleteMesaById(@PathVariable("idMesa") Integer idMesa){
        this.iMesaService.deleteMesaById(idMesa);
        return new ResponseEntity<>("Se ha eliminado la mesa con dicho id",HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("updatePositionMesa/{idMesa}/{newPosition}")
    @ApiOperation("Dado un idMesa y una newPosition, actualiza la posicion de la mesa con dicho idMesa")
    public ResponseEntity<String> updatePositionMesa(@PathVariable("idMesa") Integer idMesa,
                                                     @PathVariable("newPosition") Integer position){
        this.iMesaService.updatePositionMesa( idMesa, position);
        return new ResponseEntity<>("Se ha actualizado la posicion de la mesa",HttpStatus.OK);
    }








}
