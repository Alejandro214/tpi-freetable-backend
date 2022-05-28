package com.restaurant.restaurantApi.controller;

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
        return new ResponseEntity<>(this.iProductService.saveProduct(product), HttpStatus.OK);
    }
    @ApiOperation(value = "Busca el producto con ese nombre",notes = "Retorna el producto con el nombre que se pasa por la url")
    @GetMapping("searchProduct/{name}")
    public ResponseEntity<Product> searchProduct(@PathVariable("name") String name){
        return new ResponseEntity<>(this.iProductService.searchProduct(name),HttpStatus.OK);

    }

    @ApiOperation(value = "Retorna todos los productos", notes = "Retorna todos los productos que es restaurante tiene")
    @GetMapping("getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(this.iProductService.getAllProducts(),HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna paginas que tengan esa palabra en su nombre", notes = "Retorna todos los productos que contengan al menos esa letra o palabra en su nombre")
    @GetMapping("getFilterProductByName/{name}")
    public ResponseEntity<List<Product>> filterProductByName(
            @PageableDefault(size = 10 , page = 0, direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable("name") String name){
        return new ResponseEntity<>(this.iProductService.filterProductByName(pageable, name),HttpStatus.OK);
    }

    @ApiOperation(value= "Retornas los productos que pertenece a esa categoria",notes = "Dada una categoria, retornas todos los productos que perenezcan a esa categoria")
    @GetMapping("getProductsByCategory/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("category") Integer category){
        return new ResponseEntity<>(this.iProductService.getProductsByCategory(category),HttpStatus.OK);
    }


    @ApiOperation(value = "Retorna el producto de la categoria seleccionada")
    @GetMapping("categoryById/{idCategory}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("idCategory") Integer idCategory){
        List<Product> categories = this.iProductService.productsByCategory(idCategory);
        return new ResponseEntity<>(categories,HttpStatus.OK);

    }

    @ApiOperation(value = "Retorna el producto de la categoria seleccionada")
    @GetMapping("categoryByNameCategory/{nameCategory}")
    public ResponseEntity<List<Product>> getProductByNameCategory(@PathVariable("nameCategory") String nameCategory){
        List<Product> categories = this.iProductService.productscategoryByNameCategory(nameCategory);
        return new ResponseEntity<>(categories,HttpStatus.OK);

    }

    @ApiOperation(value = "Retorna la cantida de productos segun la categoria")
    @GetMapping("cantProductosByNameCategory/{nameCategory}")
    public ResponseEntity<Integer> getCantProductosByNameCategory(@PathVariable("nameCategory") String nameCategory) {
        return new ResponseEntity<>(this.iProductService.cantProductosByNameCategory(nameCategory),HttpStatus.OK);
    }


}
