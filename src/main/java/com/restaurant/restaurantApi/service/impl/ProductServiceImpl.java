package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.dto.ProductResponse;
import com.restaurant.restaurantApi.mapper.IProductMapper;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

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
        List<Product> listProduct                 = (List<Product>) this.iProductRepo.findAll();
        List<ProductResponse> listProductResponse = this.iProductMapper.listProductsToListProductResponse(listProduct);
        return  listProductResponse;
    }

    @Override
    public List<ProductResponse> filterProductByName(Pageable pageable, String name) {
        List<Product>         listProduct         = this.iProductRepo.filterProductByName(pageable,name).getContent();
        List<ProductResponse> listProductResponse = this.iProductMapper.listProductsToListProductResponse(listProduct);

        return listProductResponse;
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Integer category) {
        List<Product>         listProduct         = this.iProductRepo.findAllByCategory(category);
        List<ProductResponse> listProductResponse = this.iProductMapper.listProductsToListProductResponse(listProduct);
        return listProductResponse;
    }

}
