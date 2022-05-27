package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Category;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.ICategoryRepo;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepo iProductRepo;

    @Autowired
    private ICategoryRepo categoryRepo;


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

    @Override
    public List<Product> productsByCategory(Integer idCategory) {
        Category category = this.categoryRepo.findById(idCategory).orElse(null);
        if (category == null)
            return new ArrayList<>();
        return category.getProducts();
    }

    @Override
    public List<Product> productscategoryByNameCategory(String nameCategory) {
       Category category = this.categoryRepo.findByNameCategory(nameCategory);
       return category.getProducts();
    }

    @Override
    public Integer cantProductosByNameCategory(String nameCategory) {
        return this.categoryRepo.cantProductosByNameCategory(nameCategory);
    }

}
