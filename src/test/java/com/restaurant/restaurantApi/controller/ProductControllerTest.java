package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.jwt.JwtProvider;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtProvider jwtProvider;

    private String token;

    @Autowired
    private IProductRepo iProductRepo;


    @BeforeEach
    public void setUp() {
        this.token = this.jwtProvider.generateTokenByUsername("admin");
        Product product = new Product();
        product.setPrice(1000d);
        product.setName("productTest");
        product.setImage("imagenTest");
        product.setCantProduct(1);
        this.iProductRepo.save(product);
    }


    @Test
    public void buscar_una_producto_por_el_nombre() throws Exception {
        this.mockMvc.perform(get("/product/getFilterProductByName/productTest").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].idProduct").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("productTest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].image").value("imagenTest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value(1000d));
    }

    @Test
    public void error_token_no_enviado_al_intentar_buscar_una_producto_por_el_nombre() throws Exception {
        this.mockMvc.perform(get("/product/getFilterProductByName/papas"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }




}
