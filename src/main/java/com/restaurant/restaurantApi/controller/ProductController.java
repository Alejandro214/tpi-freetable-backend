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

    @ApiOperation(value = "Retorna todos los productos", notes = "Retorna todos los productos que es restaurante tiene")
    @GetMapping("getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(@PageableDefault(size = 10000 , page = 0, direction = Sort.Direction.DESC) Pageable pageable){
        try {
        return new ResponseEntity<>(this.iProductService.getAllProducts(pageable),HttpStatus.OK);
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

    @GetMapping("getCantProductByIdMesaAndIdOrder/{idProduct}/{idMesa}/{idOrder}")
    @ApiOperation("Dado un idProduct, idMesa y idOrder, retorna la cantidad de dicho producto que pertenece a una orden de una mesa en particular")
    public  ResponseEntity<Integer> getCantProductByIdMesaAndIdOrder(@PathVariable("idProduct") Integer idProduct,
                                                                     @PathVariable("idMesa") Integer idMesa,
                                                                     @PathVariable("idOrder") Integer idOrder){
        return new ResponseEntity<>(this.iProductService.getCantProductByIdMesaAndIdOrder(idProduct,idMesa,idOrder),HttpStatus.OK);
    }

    @GetMapping("getCantProducts")
    @ApiOperation("Retorna el total de los productos que tiene el restaurant ")
    public ResponseEntity<Integer> getCantProducts(){
        return new ResponseEntity<>(this.iProductService.getCantProducts(),HttpStatus.OK);
    }

    @GetMapping("getProductByName/{nameProduct}")
    @ApiOperation("Retorna el producto con el nombre que recibe")
    public ResponseEntity<Product> getProductByName(@PathVariable("nameProduct") String nameProduct){
        return new ResponseEntity<>(this.iProductService.getProductByName(nameProduct),HttpStatus.OK);
    }


}
