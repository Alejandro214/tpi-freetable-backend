package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Category;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.inter.ICategoryService;
import com.restaurant.restaurantApi.service.inter.IOrderService;
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

@RestController
@RequestMapping("menu")
@Api
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MenuController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

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

    @ApiOperation(value = "Retorna todos los productos", notes = "Retorna todos los productos que es restaurante tiene")
    @GetMapping("getAllProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return new ResponseEntity<>(this.iProductService.getAllProducts(),HttpStatus.OK);
    }


    @ApiOperation(value = "Retorna paginas que tengan esa palabra en su nombre", notes = "Retorna todos los productos que contengan al menos esa letra o palabra en su nombre")
    @GetMapping("getFilterProductByName/{name}")
    public ResponseEntity<List<ProductResponse>> filterProductByName(
            @PageableDefault(size = 10 , page = 0, direction = Sort.Direction.DESC)Pageable pageable,
            @PathVariable("name") String name){
        return new ResponseEntity<>(this.iProductService.filterProductByName(pageable, name),HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna la categoria que pertence a ese numero",notes = "Retorna la categoria perteneciente a ese numero" )
    @GetMapping("getCategoryByCategory/{category}")
    public ResponseEntity<Category> getCategoryByCategory(@PathVariable("category") Integer category){
        return new ResponseEntity<>(this.iCategoryService.findByCategory(category),HttpStatus.OK);
    }

    @GetMapping("getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(this.iOrderService.getAllOrders(),HttpStatus.OK);
    }
}
