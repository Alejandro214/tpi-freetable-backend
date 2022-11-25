package com.restaurant.restaurantApi.controller;


import com.restaurant.restaurantApi.jwt.JwtProvider;
import com.restaurant.restaurantApi.model.SettingUser;
import com.restaurant.restaurantApi.repo.ISettingUserRepo;
import com.restaurant.restaurantApi.service.inter.ISettingUserService;
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


    @Autowired
    private ISettingUserService settingUserService;


    @BeforeEach
    public void setUp() {

        this.token = this.jwtProvider.generateTokenByUsername("admin");

        SettingUser settingUser = new SettingUser();
        settingUser.setImagenData("imagenData");
        settingUser.setNombreUsuario("testPrueba");
        settingUser.setCantMesas(20);
        this.settingUserService.saveSettingUser(settingUser);
    }

    @Test
    public void buscar_configuracion_del_usuario_por_username() throws Exception {
        this.mockMvc.perform(get("/setting/getSettingByUsername/testPrueba").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idSettingUser").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreUsuario").value("testPrueba"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantMesas").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imagenData").value("imagenData"));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.idSettingUser").exists())
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
