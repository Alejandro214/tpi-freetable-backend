package com.restaurant.restaurantApi.service.inter;

import com.restaurant.restaurantApi.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IProductService {

    Product saveProduct(Product product);

    Product searchProduct(String name);

    List<Product> getAllProducts();

    Set<Product> filterProductByName(Pageable pageable, String name);

    List<Product> productscategoryByNameCategory(String nameCategory);

    Integer cantProductosByNameCategory(String nameCategory);

    void deleteProductOrder(Integer idOrder,Product product);

    void updateProduct(Integer idProduct,Integer newCant);

    Integer getCantProductByIdMesaAndIdOrder(Integer idProduct,Integer idMesa,Integer idOrder);

    void reemplazarProductOrder(Integer idProductAReemplazar,Integer idOrder,Integer idProductACambiar);
}