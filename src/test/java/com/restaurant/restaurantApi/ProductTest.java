package com.restaurant.restaurantApi;

import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import com.restaurant.restaurantApi.service.inter.IProductService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTest {
    @Autowired
    private IProductService productService;

    @Autowired
    private IMesaService mesaService;

    @Autowired
    private IOrderService orderService;

    private Product pizaa = new Product();

    @Before("before")
    public void setup() {
        pizaa.setCategory(1);
        pizaa.setName("Pizza");
        pizaa.setImage("Una imagen");
        pizaa.setDescription("Pizza a la piedra");
        pizaa.setPrice(500d);
        pizaa.setListPedidos(new ArrayList<>());

    }

    @Test()
    void save_product_pizza() {
        setup();
        Product product = this.productService.saveProduct(this.pizaa);
        assertEquals(product.getCategory(),this.pizaa.getCategory());
        assertEquals(product.getImage(),this.pizaa.getImage());
        assertEquals(product.getDescription(),this.pizaa.getDescription());
        assertEquals(product.getName(),this.pizaa.getName());
        assertEquals(product.getPrice(),this.pizaa.getPrice());
        assertNotNull(product.getIdProduct());

    }

    @Test
    void search_product_fanta(){
        Product fanta = this.productService.searchProduct("Fanta");
        assertEquals(fanta.getCategory(),2);
        assertEquals(fanta.getImage(),"https://superlago.com.ar/wp-content/uploads/2021/01/7790895000454.jpg");
        assertEquals(fanta.getDescription(),"Gaseosa fanta mediana");
        assertEquals(fanta.getName(),"Fanta");
        assertEquals(fanta.getPrice(),200d);
        assertNotNull(fanta.getIdProduct());
    }

    @Test
    void get_All_Products(){
        List<Product> products = this.productService.getAllProducts();
        assertNotNull(products);
        assertTrue(products.size() >= 1);
        Product product = products.get(0);
        assertNotNull(product.getCategory());
        assertNotNull(product.getImage());
        assertNotNull(product.getDescription());
        assertNotNull(product.getName());
        assertNotNull(product.getPrice());
        assertNotNull(product.getIdProduct());

    }
}
