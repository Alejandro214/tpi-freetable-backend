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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class SettingUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @Test
    public void getSettingByUsername() throws Exception {
        this.mockMvc.perform(get("/setting/getSettingByUsername/admin").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idSettingUser").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreUsuario").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantMesas").value(10));
    }

    @Test
    public void saveSettingUser() throws Exception {
        this.mockMvc.perform(post("/setting/saveSettingUser").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreUsuario\":\"mariobros\",\"cantMesas\":16}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idSettingUser").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreUsuario").value("mariobros"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantMesas").value(16));
    }

}
