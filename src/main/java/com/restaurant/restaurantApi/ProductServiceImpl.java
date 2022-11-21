package com.restaurant.restaurantApi;

import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepo iProductRepo;

    @Override
    public List<Product> filterProductByName(String name) {
        return this.iProductRepo.findAllProducts(name);
    }

}
