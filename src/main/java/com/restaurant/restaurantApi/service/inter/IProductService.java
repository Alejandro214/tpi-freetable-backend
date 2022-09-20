package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IProductService {

    Product saveProduct(Product product);



    List<Product> getAllProducts(Pageable pageable);

    Set<Product> filterProductByName(Pageable pageable, String name);

    void deleteProductOrder(Integer idOrder,Product product);

    void updateProduct(Integer idProduct,Integer newCant);

    Integer getCantProductByIdMesaAndIdOrder(Integer idProduct,Integer idMesa,Integer idOrder);

    void reemplazarProductOrder(Integer idProductAReemplazar,Integer idOrder,Integer idProductACambiar);

    Integer getCantProducts();

    Product getProductByName(String name);
}