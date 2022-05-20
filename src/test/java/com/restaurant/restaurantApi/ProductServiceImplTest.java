package com.restaurant.restaurantApi;

import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestComponent
public class ProductServiceImplTest {

    @Mock
    private IProductRepo iProductRepo;

    @InjectMocks
    private ProductServiceImpl productService;


    private Product pizza = new Product();

    private Product fanta = new Product();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pizza.setCategory(1);
        pizza.setIdProduct(1);
        pizza.setName("Pizza");
        pizza.setImage("Una imagen");
        pizza.setDescription("Pizza a la piedra");
        pizza.setPrice(500d);
        pizza.setListPedidos(new ArrayList<>());

        fanta.setIdProduct(2);
        fanta.setCategory(2);
        fanta.setImage("https://superlago.com.ar/wp-content/uploads/2021/01/7790895000454.jpg");
        fanta.setDescription("Gaseosa fanta mediana");
        fanta.setName("Fanta");
        fanta.setPrice(200d);



    }

    @Test
    void save_product_pizza() {
        when(iProductRepo.save(any(Product.class))).thenReturn(pizza);
        Product product = this.productService.saveProduct(this.pizza);
        assertEquals(product.getCategory(),this.pizza.getCategory());
        assertEquals(product.getImage(),this.pizza.getImage());
        assertEquals(product.getDescription(),this.pizza.getDescription());
        assertEquals(product.getName(),this.pizza.getName());
        assertEquals(product.getPrice(),this.pizza.getPrice());
        assertEquals(product.getIdProduct(),1);

    }

    @Test
    void search_product_fanta(){
        when(iProductRepo.findByName(any(String.class))).thenReturn(fanta);
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
        when(iProductRepo.findAll()).thenReturn(asList(pizza,fanta));
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

    @Test
    void filter_Product_By_Name(){
        List<Product> array = new ArrayList<>();
        array.add(fanta);
        Page<Product> page = new PageImpl<>(array);
        when(iProductRepo.filterProductByName(any(Pageable.class),any(String.class)))
                .thenReturn(page);
        PageRequest pageable = PageRequest.of(2,20);
        List<Product> products = this.productService.filterProductByName(pageable,"Fa");
        assertNotNull(products);
        assertEquals(1, products.size());
        System.out.println(products.get(0));
        assertTrue(products.get(0).getName().contains("Fa"));



    }

    @Test
    void get_Products_By_Category(){
        when(iProductRepo.findAllByCategory(any(Integer.class))).thenReturn(asList(fanta));
        List<Product> products = this.productService.getProductsByCategory(2);
        assertNotNull(products);
        assertTrue(products.size() >= 1);
        assertEquals(products.get(0).getCategory(),2);
    }
}
