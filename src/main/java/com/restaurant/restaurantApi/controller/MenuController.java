package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.IOrderService;
import com.restaurant.restaurantApi.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
@Api
public class MenuController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IProductService iProductService;

    @ApiOperation(value = "Create a new order",notes = "Retorna el pedido que se creo y guardo en la base")
    @PostMapping("saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
        return new ResponseEntity<Order>(this.iOrderService.saveOrder(order), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new product",notes = "Retorna el producto que se creo y guardo en la base")
    @PostMapping("saveProduct")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody Product product){
        return new ResponseEntity<ProductResponse>(this.iProductService.saveProduct(product),HttpStatus.OK);
    }
    @ApiOperation(value = "Busca el producto con ese nombre",notes = "Retorna el producto con el nombre que se pasa por la url")
    @GetMapping("searchProduct/{name}")
    public ResponseEntity<ProductResponse> searchProduct(@PathVariable("name") String name){
        return new ResponseEntity<ProductResponse>(this.iProductService.searchProduct(name),HttpStatus.OK);

    }
}
