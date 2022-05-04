package com.restaurant.restaurantApi.mapper;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Product;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

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
        product.setCategory(productResponse.getCategory());
        return product;
    }

    @Override
    public List<ProductResponse> listProductsToListProductResponse(List<Product> productList) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product p:productList){
            ProductResponse productResponse = this.productToProductResponse(p);
            productResponseList.add(productResponse);
        }
        return productResponseList;
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
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }
}
