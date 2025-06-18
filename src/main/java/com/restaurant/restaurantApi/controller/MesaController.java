package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.dto.Mensaje;
import com.restaurant.restaurantApi.model.*;
import com.restaurant.restaurantApi.service.inter.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("mesa")
@Tag(name = "Mesa Controller", description = "API para gestión de mesas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MesaController {

    @Autowired
    private IMesaService iMesaService;

    @Operation(summary = "Crear una mesa", description = "Guarda una nueva mesa en la base de datos.")
    @PostMapping("/mesas")
    public ResponseEntity<?> saveMesa(@Valid @RequestBody Mesa mesa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new Mensaje("Error, campos inválidos"));
        }
        Mesa savedMesa = iMesaService.saveMesa(mesa);
        return ResponseEntity.ok(savedMesa);
    }

    @Operation(summary = "Obtener mesa por ID", description = "Retorna la mesa que corresponde al idMesa indicado.")
    @GetMapping("/mesas/{idMesa}")
    public ResponseEntity<Mesa> getMesaById(@PathVariable Integer idMesa) {
        Mesa mesa = iMesaService.getMesaById(idMesa);
        return ResponseEntity.ok(mesa);
    }

    @Operation(summary = "Listar todas las mesas", description = "Retorna todas las mesas actuales del restaurante.")
    @GetMapping("/mesas")
    public ResponseEntity<List<Mesa>> findAllMesas() {
        List<Mesa> mesas = iMesaService.findAllMesas();
        return ResponseEntity.ok(mesas);
    }

    @Operation(summary = "Agregar pedido a mesa", description = "Agrega un pedido a la mesa indicada y retorna la mesa actualizada.")
    @PutMapping("/mesas/{idMesa}/orders")
    public ResponseEntity<?> addOrderByIdMesa(@PathVariable Integer idMesa, @Valid @RequestBody Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new Mensaje("Error, campos inválidos"));
        }
        Mesa mesa = iMesaService.addOrderByIdMesa(idMesa, order);
        return ResponseEntity.ok(mesa);
    }

    @Operation(summary = "Cambiar estado de mesa", description = "Actualiza el estado de la mesa indicada y retorna la mesa actualizada.")
    @PutMapping("/mesas/{idMesa}/estado/{newEstadoMesa}")
    public ResponseEntity<Mesa> changeEstadoMesa(@PathVariable Integer idMesa, @PathVariable String newEstadoMesa) {
        Mesa mesa = iMesaService.changeEstadoMesa(idMesa, newEstadoMesa);
        return ResponseEntity.ok(mesa);
    }

    @Operation(summary = "Eliminar mesa por ID", description = "Elimina la mesa que corresponde al idMesa indicado.")
    @DeleteMapping("/mesas/{idMesa}")
    public ResponseEntity<Mensaje> deleteMesaById(@PathVariable Integer idMesa) {
        iMesaService.deleteMesaById(idMesa);
        return ResponseEntity.ok(new Mensaje("Se ha eliminado la mesa con dicho id"));
    }

    @Operation(summary = "Actualizar posición de mesa", description = "Actualiza la posición de la mesa indicada y retorna la mesa actualizada.")
    @PutMapping("/mesas/{idMesa}/posicion/{newPosition}")
    public ResponseEntity<Mesa> updatePositionMesa(@PathVariable Integer idMesa, @PathVariable Integer newPosition) {
        Mesa updatedMesa = iMesaService.updatePositionMesa(idMesa, newPosition);
        return ResponseEntity.ok(updatedMesa);
    }

    @Operation(summary = "Juntar dos mesas", description = "Une dos mesas y retorna la mesa resultante.")
    @PostMapping("/mesas/juntar/{idMesaUno}/{idMesaDos}")
    public ResponseEntity<Mesa> juntarMesas(@PathVariable Integer idMesaUno, @PathVariable Integer idMesaDos) {
        Mesa mesa = iMesaService.juntarMesas(idMesaUno, idMesaDos);
        return ResponseEntity.ok(mesa);
    }








}
