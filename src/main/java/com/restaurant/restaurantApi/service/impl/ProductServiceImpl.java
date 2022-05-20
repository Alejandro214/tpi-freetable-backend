package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Product searchProduct(String name) {
        return this.iProductRepo.findByName(name);

    }

    @Override
    public List<Product> getAllProducts() {
        return  (List<Product>) this.iProductRepo.findAll();
    }

    @Override
    public List<Product> filterProductByName(Pageable pageable, String name) {
        return  this.iProductRepo.filterProductByName(pageable,name).getContent();
    }

    @Override
    public List<Product> getProductsByCategory(Integer category) {
        return this.iProductRepo.findAllByCategory(category);
    }

}
