package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IProductService {

    Product saveProduct(Product product);

    List<Product> getAllProducts(Pageable pageable);

    Set<Product> filterProductByName(Pageable pageable, String name);

    Integer getCantProductByIdMesaAndIdOrder(Integer idProduct,Integer idMesa,Integer idOrder);

    Integer getCantProducts();

    Product getProductByName(String name);
}