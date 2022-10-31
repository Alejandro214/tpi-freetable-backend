package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepo iProductRepo;

    @Override
    public Product saveProduct(Product product) {
        return this.iProductRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts(Pageable pageable) {return   this.iProductRepo.findAll(pageable).getContent();}

    @Override
    public List<Product> filterProductByName(String name) {
        return this.iProductRepo.findAllProducts(name);
    }


    @Override
    public Product getProductByName(String name) {
        return this.iProductRepo.findByName(name);
    }

}
