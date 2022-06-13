package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Category;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.ICategoryRepo;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepo iProductRepo;

    @Autowired
    private ICategoryRepo categoryRepo;

    @Autowired
    private IOrderRepo iOrderRepo;


    @Override
    public Product saveProduct(Product product) {
        return this.iProductRepo.save(product);
    }

    @Override
    public Product searchProduct(String name) {
        return this.iProductRepo.findByName(name);

    }

    @Override
    public List<Product> getAllProducts(Pageable pageable) {

        return   this.iProductRepo.findAll(pageable).getContent();
    }

    @Override
    public Set<Product> filterProductByName(Pageable pageable, String name) {
        List<Product> products = this.getAllProducts(pageable);
        Set<Product> resultProductsFilter = products.stream().filter(product -> product.getName().toLowerCase().contains(name.toLowerCase())
                || product.getListCategory().stream().anyMatch(category -> category.getNameCategory().toLowerCase().contains(name.toLowerCase()))).collect(Collectors.toSet());
        return resultProductsFilter;
    }


    @Override
    public List<Product> productscategoryByNameCategory(String nameCategory,Pageable pageable) {
        if(nameCategory.equals("Todos"))
            return this.getAllProducts(pageable);
       Category category = this.categoryRepo.findByNameCategory(nameCategory);
       return category.getProducts();
    }

    @Override
    public Integer cantProductosByNameCategory(String nameCategory) {
        return this.categoryRepo.cantProductosByNameCategory(nameCategory);
    }

    @Override
    public void deleteProductOrder(Integer idOrder, Product product) {
        Order order = this.iOrderRepo.findById(idOrder).get();
        this.iProductRepo.deleteProductDeOrder(product.getIdProduct(),idOrder);
        order.deleteProduct(product);
        this.iOrderRepo.save(order);
    }

    @Override
    public void updateProduct(Integer idProduct,Integer newCant) {
         this.iProductRepo.updateCantProductById(idProduct,newCant);
    }

    @Override
    public Integer getCantProductByIdMesaAndIdOrder(Integer idProduct, Integer idMesa, Integer idOrder) {
        return this.iProductRepo.getCantProductByIdMesaAndIdOrder(idProduct, idMesa, idOrder);
    }

    @Override
    public void reemplazarProductOrder(Integer idProductAReemplazar, Integer idOrder, Integer idProductACambiar) {
        this.iProductRepo.reemplazarProductOrder(idProductAReemplazar,idOrder,idProductACambiar);
    }

}
