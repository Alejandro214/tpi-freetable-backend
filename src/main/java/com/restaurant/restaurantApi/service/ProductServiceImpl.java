package com.restaurant.restaurantApi.service;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.mapper.IProductMapper;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductRepo iProductRepo;

    @Autowired
    private IProductMapper iProductMapper;

    @Override
    public ProductResponse saveProduct(Product product) {
        Product          product1 = this.iProductRepo.save(product);
        ProductResponse  productResponse = this.iProductMapper.productToProductResponse(product1);
        return productResponse;
    }

    @Override
    public ProductResponse searchProduct(String name) {
        Product product = this.iProductRepo.findByName(name);
        ProductResponse  productResponse = this.iProductMapper.productToProductResponse(product);
        return productResponse;

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        System.out.println(this.iProductRepo.findAll());
        List<Product> listProduct                 = (List<Product>) this.iProductRepo.findAll();
        List<ProductResponse> listProductResponse = this.iProductMapper.listProductsToListProductResponse(listProduct);
        return  listProductResponse;
    }
}
