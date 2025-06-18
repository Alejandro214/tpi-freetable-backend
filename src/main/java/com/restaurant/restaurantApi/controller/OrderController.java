package com.restaurant.restaurantApi.controller;


import com.restaurant.restaurantApi.dto.Mensaje;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.service.inter.IOrderService;
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
@RequestMapping("order")
@Tag(name = "Order Controller", description = "API para gestión de ordenes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @Operation(
            summary = "Crear un nuevo pedido",
            description = "Guarda un nuevo pedido en la base de datos dado un objeto Order válido."
    )
    @PostMapping("/orders")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new Mensaje("Error, campos inválidos"));
        }
        Order savedOrder = iOrderService.saveOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @Operation(
            summary = "Listar pedidos pagados por mesa en un rango de fechas",
            description = "Retorna todos los pedidos pagados realizados en la mesa indicada entre las fechas 'from' y 'to'."
    )
    @GetMapping("/orders/paid/{idMesa}")
    public ResponseEntity<List<Order>> getAllOrdersPagados(
            @PathVariable Integer idMesa,
            @RequestParam String from,
            @RequestParam String to) {
        List<Order> orders = iOrderService.getAllOrdersPagados(idMesa, from, to);
        return ResponseEntity.ok(orders);
    }

    @Operation(
            summary = "Eliminar pedido por ID",
            description = "Elimina el pedido que corresponde al idOrder indicado."
    )
    @DeleteMapping("/orders/{idOrder}")
    public ResponseEntity<Mensaje> deleteOrder(@PathVariable Integer idOrder) {
        iOrderService.deleteOrder(idOrder);
        return ResponseEntity.ok(new Mensaje("Se ha eliminado el pedido"));
    }

    @Operation(
            summary = "Obtener pedido confirmado por ID de mesa",
            description = "Retorna el pedido confirmado asociado al idMesa indicado."
    )
    @GetMapping("/orders/confirmed/{idMesa}")
    public ResponseEntity<Order> getOrderConfirmado(@PathVariable Integer idMesa) {
        Order order = iOrderService.getPedidoConfirmadoByIdMesa(idMesa);
        return ResponseEntity.ok(order);
    }

    @Operation(
            summary = "Pagar pedido por ID de mesa",
            description = "Marca como pagado el pedido asociado al idMesa indicado."
    )
    @PutMapping("/orders/pay/{idMesa}")
    public ResponseEntity<Order> pagarPedidoByMesaIdMesa(@PathVariable Integer idMesa) {
        Order paidOrder = iOrderService.pagarPedidoByMesaIdMesa(idMesa);
        return ResponseEntity.ok(paidOrder);
    }





}
