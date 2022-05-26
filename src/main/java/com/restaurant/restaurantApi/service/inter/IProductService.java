package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    Product saveProduct(Product product);

    Product searchProduct(String name);

    List<Product> getAllProducts();

    List<Product> filterProductByName(Pageable pageable, String name);

    List<Product> getProductsByCategory(Integer category);

    List<Product> productsByCategory(Integer idCategory);
}