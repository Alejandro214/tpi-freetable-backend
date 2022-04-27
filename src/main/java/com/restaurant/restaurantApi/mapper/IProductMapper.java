package com.restaurant.restaurantApi.mapper;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Product;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


public interface IProductMapper {
    @Mappings({
            @Mapping(target="name", source="productResponse.name" ),
            @Mapping(target="image", source="productResponse.image"),
            @Mapping(target="price", source="productResponse.price"),
            @Mapping(target="description", source="productResponse.description"),
    })
    ProductResponse productToProductResponse(Product product);
    Product         productResponseToProduct(ProductResponse productResponse);
    List<ProductResponse>   listProductsToListProductResponse(List<Product> productList);



}
