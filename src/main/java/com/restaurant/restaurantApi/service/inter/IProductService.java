package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IProductService {

    Product saveProduct(Product product);

    Product searchProduct(String name);

    List<Product> getAllProducts();

    Set<Product> filterProductByName(Pageable pageable, String name);

    List<Product> productsByCategory(Integer idCategory);

    List<Product> productscategoryByNameCategory(String nameCategory);

    Integer cantProductosByNameCategory(String nameCategory);

    void deleteProductOrder(Integer idOrder,Product product);
}