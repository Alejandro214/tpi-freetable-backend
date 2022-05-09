package com.restaurant.restaurantApi.mapper;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class ProductMapperImpl implements IProductMapper{

    @Autowired
    private IProductRepo iProductRepo;


    @Override
    public Product productResponseToProduct(ProductResponse productResponse) {
        if(productResponse == null)
            return null;
        Product product = iProductRepo.findByName(productResponse.getName());
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
    public List<Product> listProductsResponseToListProduct(List<ProductResponse> productResponseList) {
        List<Product> productList = new ArrayList<>();
        for(ProductResponse p:productResponseList){
            Product product = this.productResponseToProduct(p);
            productList.add(product);
        }
        return productList;
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
