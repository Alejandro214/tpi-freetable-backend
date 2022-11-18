package com.restaurant.restaurantApi.service.impl;

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

    private String token;


    @BeforeEach
    public void setUp() {

    }


    @Test
    public void filterProductByName() throws Exception {
        this.mockMvc.perform(get("/product/getFilterProductByName/papas").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].idProduct").value(49))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Pastel de papas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].image").value("https://www.cucinare.tv/wp-content/uploads/2018/11/Pastel-de-papas.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value(400d));
    }
}
