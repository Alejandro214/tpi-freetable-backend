package com.restaurant.restaurantApi.mapper;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ProductMapperImpl implements IProductMapper{


    @Override
    public Product productResponseToProduct(ProductResponse productResponse) {
        if(productResponse == null)
            return null;
        Product product = new Product();
        product.setDescription(productResponse.getDescription());
        product.setImage(productResponse.getImage());
        product.setPrice(productResponse.getPrice());
        product.setName(productResponse.getName());
        return product;
    }

    @Override
    public ProductResponse productToProductResponse(Product product) {
        if(product == null)
            return null;
        ProductResponse productResponse = new ProductResponse();
        productResponse.setDescription(product.getDescription());
        productResponse.setImage(product.getImage());
        productResponse.setPrice(product.getPrice());
        productResponse.setName(product.getName());
        return productResponse;
    }
}
