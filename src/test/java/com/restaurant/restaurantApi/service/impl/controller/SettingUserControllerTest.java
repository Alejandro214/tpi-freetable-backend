package com.restaurant.restaurantApi.service.impl.controller;


import com.restaurant.restaurantApi.jwt.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private JwtProvider jwtProvider;

    private String token;


    @BeforeEach
    public void setUp() {
        this.token = this.jwtProvider.generateTokenByUsername("admin");
    }

    @Test
    public void buscar_configuracion_del_usuario_por_username() throws Exception {
        this.mockMvc.perform(get("/setting/getSettingByUsername/admin").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idSettingUser").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreUsuario").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantMesas").value(10));
    }

    @Test
    public void error_token_no_enviado_al_intentar_buscar_configuracion_del_usuario_por_username() throws Exception {
        this.mockMvc.perform(get("/setting/getSettingByUsername/admin"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void guardar_configuracion_de_usuario() throws Exception {
        this.mockMvc.perform(post("/setting/saveSettingUser").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreUsuario\":\"mariobros\",\"cantMesas\":16}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idSettingUser").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreUsuario").value("mariobros"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantMesas").value(16));
    }

    @Test
    public void bad_request_campos_invalidos_al_intentar_guardar_configuracion_de_usuario() throws Exception {
        this.mockMvc.perform(post("/setting/saveSettingUser").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ErrornombreUsuario\":\"mariobros\",\"cantMesas\":16}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Error, campos invalidos"));
    }

    @Test
    public void error_token_no_enviado_al_intentar_guardar_configuracion_de_usuario() throws Exception {
        this.mockMvc.perform(post("/setting/saveSettingUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreUsuario\":\"mariobros\",\"cantMesas\":16}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
