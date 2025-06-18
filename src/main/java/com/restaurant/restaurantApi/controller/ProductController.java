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

    @Operation(
            summary = "Buscar productos por nombre",
            description = "Retorna una lista de productos que contienen la palabra o letra indicada en su nombre."
    )
    @GetMapping("/products")
    public ResponseEntity<List<Product>> filterProductsByName(@RequestParam("name") String name) {
        List<Product> filteredProducts = iProductService.filterProductByName(name);
        return ResponseEntity.ok(filteredProducts);
    }

}
