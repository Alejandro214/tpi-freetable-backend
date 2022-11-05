package com.restaurant.restaurantApi.service.impl;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.ArrayList;
import java.util.List;


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
        pizza.setIdProduct(1);
        pizza.setName("Pizza");
        pizza.setImage("Una imagen");
        pizza.setPrice(500d);
        pizza.setListPedidos(new ArrayList<>());

        fanta.setIdProduct(2);
        fanta.setImage("https://superlago.com.ar/wp-content/uploads/2021/01/7790895000454.jpg");
        fanta.setName("Fanta");
        fanta.setPrice(200d);



    }


    @Test
    void filter_Product_By_Name() {
        List<Product> array = new ArrayList<>();
        array.add(fanta);
        Page<Product> page = new PageImpl<>(array);
    }


}
