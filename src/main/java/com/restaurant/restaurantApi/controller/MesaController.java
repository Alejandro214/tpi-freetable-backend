package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.dto.Mensaje;
import com.restaurant.restaurantApi.model.*;
import com.restaurant.restaurantApi.service.inter.*;
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
@RequestMapping("mesa")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MesaController {

    @Autowired
    private IMesaService iMesaService;

    @ApiOperation(value = "Dada un mesa, la guarda en la base")
    @PostMapping("saveMesa")
    public ResponseEntity<Mesa> saveMesa(@Valid @RequestBody Mesa mesa, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Error, campos invalidos"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(this.iMesaService.saveMesa(mesa), HttpStatus.OK);

    }

    @ApiOperation(value = "Dado un idMesa, retorna la mesa con dicho idMesa")
    @GetMapping("getMesaById/{idMesa}")
    public  ResponseEntity<Mesa> getMesaById(@PathVariable("idMesa") Integer idMesa){
            return new ResponseEntity<>(this.iMesaService.getMesaById(idMesa),HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna todas las mesas actuales que tiene el restaurante")
    @GetMapping("findAllMesas")
    public ResponseEntity<List<Mesa>> findAllMesa(){
        return new ResponseEntity<>(this.iMesaService.findAllMesas(),HttpStatus.OK);
    }

    @ApiOperation(value = "Dado un idMesa y un pedido, agrega el pedido a la mesa con dicho idMesa y retorna la mesa con el pedido agregado")
    @PutMapping("addOrderMesa/{idMesa}")
    public ResponseEntity<Mesa> addOrderByIdMesa(@PathVariable("idMesa") Integer idMesa,@Valid @RequestBody Order order,BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Error, campos invalidos"), HttpStatus.BAD_REQUEST);
            Mesa mesa = this.iMesaService.addOrderByIdMesa(idMesa,order);
            return new ResponseEntity<>(mesa,HttpStatus.OK);

    }
    @PutMapping("changeEstadoMensa/{idMesa}/{newEstadoMesa}")
    @ApiOperation("Dada un idMesa y un newEstadoMesa, actualiza el estado de esta mesa, y retorna la mesa con el estado actualizado")
    public ResponseEntity<Mesa> changeEstadoMesa(@PathVariable("idMesa") Integer idMesa,
                                                 @PathVariable("newEstadoMesa") String newEstadoMesa) {
        Mesa mesa = this.iMesaService.changeEstadoMesa(idMesa,newEstadoMesa);
        return new ResponseEntity<>(mesa,HttpStatus.OK);
    }
    @DeleteMapping("deleteMesaById/{idMesa}")
    @ApiOperation("Dado un idMesa, elimmina la mesa con dicho idMesa")
    public ResponseEntity<String> deleteMesaById(@PathVariable("idMesa") Integer idMesa){
        this.iMesaService.deleteMesaById(idMesa);
        return new ResponseEntity<>("Se ha eliminado la mesa con dicho id",HttpStatus.OK);
    }

    @PutMapping("updatePositionMesa/{idMesa}/{newPosition}")
    @ApiOperation("Dado un idMesa y una newPosition, actualiza la posicion de la mesa con dicho idMesa")
    public ResponseEntity<Mesa> updatePositionMesa(@PathVariable("idMesa") Integer idMesa,
                                                     @PathVariable("newPosition") Integer position){
        this.iMesaService.updatePositionMesa( idMesa, position);
        return new ResponseEntity<>(this.iMesaService.updatePositionMesa( idMesa, position),HttpStatus.OK);
    }

    @PostMapping("juntarMesas/{idMesaUno}/{idMesaDos}")
    public ResponseEntity<Mesa> juntarMesas(@PathVariable("idMesaUno")Integer idMesaUno,
                                            @PathVariable("idMesaDos") Integer idMesaDos){
        Mesa mesa = this.iMesaService.juntarMesas(idMesaUno
        ,idMesaDos);
        return new ResponseEntity<>(mesa,HttpStatus.OK);
    }








}
