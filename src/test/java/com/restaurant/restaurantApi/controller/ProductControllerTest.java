package com.restaurant.restaurantApi.controller;

import com.restaurant.restaurantApi.jwt.JwtProvider;
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


    @BeforeEach
    public void setUp() {
        this.token = this.jwtProvider.generateTokenByUsername("admin");
    }


    @Test
    public void buscar_una_producto_por_el_nombre() throws Exception {
        this.mockMvc.perform(get("/product/getFilterProductByName/papas").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].idProduct").value(49))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Pastel de papas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].image").value("https://www.cucinare.tv/wp-content/uploads/2018/11/Pastel-de-papas.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value(400d));
    }

    @Test
    public void error_token_no_enviado_al_intentar_buscar_una_producto_por_el_nombre() throws Exception {
        this.mockMvc.perform(get("/product/getFilterProductByName/papas"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


}
