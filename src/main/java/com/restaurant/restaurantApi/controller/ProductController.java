package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.common.ExceptionMessage;
import com.restaurant.restaurantApi.exception.*;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.inter.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("product")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {


    @Autowired
    private IProductService iProductService;


    @ApiOperation(value = "Create a new product",notes = "Retorna el producto que se creo y guardo en la base")
    @PostMapping("saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        try {
            return new ResponseEntity<>(this.iProductService.saveProduct(product), HttpStatus.OK);
        }catch (Exception e){
            throw new SaveProductBadResquestException(ExceptionMessage.SAVE_PRODUCT_EXCEPTION.getValue());

        }
    }
    @ApiOperation(value = "Busca el producto con ese nombre",notes = "Retorna el producto con el nombre que se pasa por la url")
    @GetMapping("searchProduct/{name}")
    public ResponseEntity<Product> searchProduct(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(this.iProductService.searchProduct(name),HttpStatus.OK);
        }catch (Exception e){
            throw new SearchProductException(ExceptionMessage.SEARCH_PRODUCT_EXCEPTION.getValue());

        }

    }

    @ApiOperation(value = "Retorna todos los productos", notes = "Retorna todos los productos que es restaurante tiene")
    @GetMapping("getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
        return new ResponseEntity<>(this.iProductService.getAllProducts(),HttpStatus.OK);
        }catch (Exception e){
            throw new GetAllProducts(ExceptionMessage.GET_ALL_PRODUCTS_EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "Retorna paginas que tengan esa palabra en su nombre", notes = "Retorna todos los productos que contengan al menos esa letra o palabra en su nombre")
    @GetMapping("getFilterProductByName/{name}")
    public ResponseEntity<Set<Product>> filterProductByName(
            @PageableDefault(size = 10 , page = 0, direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable("name") String name){
        try {
            return new ResponseEntity<>(this.iProductService.filterProductByName(pageable, name),HttpStatus.OK);
        }catch (Exception e){
            throw new FilterProductByName(ExceptionMessage.FILTER_PRODUCTS_BY_NAME.getValue());
        }
    }

    @ApiOperation(value = "Retorna el producto de la categoria seleccionada")
    @GetMapping("categoryByNameCategory/{nameCategory}")
    public ResponseEntity<List<Product>> getProductByNameCategory(@PathVariable("nameCategory") String nameCategory){
        try {
            List<Product> categories = this.iProductService.productscategoryByNameCategory(nameCategory);
            return new ResponseEntity<>(categories,HttpStatus.OK);
        }catch (Exception e){
            throw new GetProductByNameCategory(ExceptionMessage.GET_PRODUCT_BY_NAME_CATEGORY.getValue());

        }

    }

    @ApiOperation(value = "Retorna la cantida de productos segun la categoria")
    @GetMapping("cantProductosByNameCategory/{nameCategory}")
    public ResponseEntity<Integer> getCantProductosByNameCategory(@PathVariable("nameCategory") String nameCategory) {
        try{
            return new ResponseEntity<>(this.iProductService.cantProductosByNameCategory(nameCategory),HttpStatus.OK);
        }catch (Exception e){
            throw new GetCantProductosByNameCategory(ExceptionMessage.GET_CANT_PRODUCTOS_BY_NAME_CATEGORY.getValue());
        }
    }


}
