package com.restaurant.restaurantApi.service.inter;
import com.restaurant.restaurantApi.model.Product;
import java.util.List;

public interface IProductService {
    List<Product> filterProductByName( String name);
}