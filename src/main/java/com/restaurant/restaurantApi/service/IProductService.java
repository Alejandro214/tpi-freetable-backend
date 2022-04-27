package com.restaurant.restaurantApi.service;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Product;

import java.util.List;

public interface IProductService {

    public ProductResponse saveProduct(Product product);

    ProductResponse searchProduct(String name);

    List<ProductResponse> getAllProducts();
}
