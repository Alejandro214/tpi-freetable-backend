package com.restaurant.restaurantApi.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void nuevo() throws Exception {
        this.mockMvc.perform(post("/auth/nuevo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"usuarioPrueba\",\"nombreUsuario\":\"usuarioPrueba123\",\"email\":\"usuarioPrueba@gmail.com\"," +
                                "\"password\":\"admin\",\"roles\":[\"ROLE_ADMIN\"]}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("usuario guardado"));
    }

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreUsuario\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bearer").value("Bearer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreUsuario").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rol").value("ROLE_ADMIN"));

    }
}
