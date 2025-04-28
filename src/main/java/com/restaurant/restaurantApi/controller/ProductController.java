package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.inter.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@Tag(name = "Product Controller", description = "API para gestión de los productos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {


    @Autowired
    private IProductService iProductService;

    @Operation(description = "Retorna paginas que tengan esa palabra en su nombre", method = "Retorna todos los productos que contengan al menos esa letra o palabra en su nombre")
    @GetMapping("getFilterProductByName/{name}")
    public ResponseEntity<List<Product>> filterProductByName(@PathVariable("name") String name){
            return new ResponseEntity<>(this.iProductService.filterProductByName(name),HttpStatus.OK);

    }
}
