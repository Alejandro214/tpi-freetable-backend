package com.restaurant.restaurantApi.service.impl;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class OrderControllerTest {

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
    public void saveOrder() throws Exception {
        this.mockMvc.perform(post("/order/saveOrder").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"totalPrice\":1000.0,\"dateOrder\":\"18-11-2022\"," +
                                "\"statusOrder\":\"CONFIRMADO\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idOrder").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(1000d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOrder").value("18-11-2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusOrder").value("CONFIRMADO"));
    }

    @Test
    public void getAllOrdersPagados() throws Exception {
        this.mockMvc.perform(get("/order/getAllOrdersPagados/1/18-11-2022 14:00/18-11-2022 14:00").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].idOrder").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].totalPrice").value(300d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dateOrder").value("18-11-2022 14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].statusOrder").value("PAGADO"));
    }
    @Test
    public void deleteOrder() throws Exception {
        this.mockMvc.perform(delete("/order/deleteOrder/1").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getOrderConfirmado() throws Exception {
        this.mockMvc.perform(get("/order/getOrderConfirmado/1").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idOrder").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(300d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOrder").value("18-11-2022 14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusOrder").value("CONFIRMADO"));
    }

    @Test
    public void pagarPedidoByMesaIdMesa() throws Exception {

        this.mockMvc.perform(put("/order/pagarPedidoByMesaIdMesa/1").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idOrder").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(300d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOrder").value("18-11-2022 14:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusOrder").value("PAGADO"));
    }




}
