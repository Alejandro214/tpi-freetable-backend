package com.restaurant.restaurantApi.service.impl.service;

import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.service.inter.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private IProductService productService;


    @Test
    public void buscar_un_productos_por_el_nombre(){
        List<Product> listProducts = this.productService.filterProductByName("papas");
        assertEquals(1,listProducts.size());
        assertNotNull(listProducts.get(0).getIdProduct());
        assertEquals("Pastel de papas",listProducts.get(0).getName());
        assertEquals(400d,listProducts.get(0).getPrice());
        assertEquals("https://www.cucinare.tv/wp-content/uploads/2018/11/Pastel-de-papas.jpg",listProducts.get(0).getImage());

    }
}
