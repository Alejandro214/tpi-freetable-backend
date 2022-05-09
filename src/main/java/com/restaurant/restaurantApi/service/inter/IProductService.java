package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    ProductResponse saveProduct(Product product);

    ProductResponse searchProduct(String name);

    List<ProductResponse> getAllProducts();

    List<ProductResponse> filterProductByName(Pageable pageable, String name);

    List<ProductResponse> getProductsByCategory(Integer category);
}