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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String token;


    @Test
    public void saveOrder() throws Exception {
        this.mockMvc.perform(post("/order/saveOrder").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"totalPrice\":1000.0,\"dateOrder\":\"18-11-2022\"," +
                                "\"statusOrder\":\"CONFIRMADO\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idOrder").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(1000d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOrder").value("18-11-2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusOrder").value("CONFIRMADO"));
    }

    //Sin terminar
    @Test
    public void getAllOrdersPagados() throws Exception {
        this.mockMvc.perform(post("/order/getAllOrdersPagados").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"totalPrice\":1000.0,\"dateOrder\":\"18-11-2022\"," +
                                "\"statusOrder\":\"CONFIRMADO\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idOrder").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(1000d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOrder").value("18-11-2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusOrder").value("CONFIRMADO"));
    }
    //Sin terminar
    @Test
    public void deleteOrder() throws Exception {
        this.mockMvc.perform(delete("/order/deleteOrder/1").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //Sin teminar
    @Test
    public void getOrderConfirmado() throws Exception {
        this.mockMvc.perform(get("/order/getOrderConfirmado/1").header("Authorization", "Bearer " + "token"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idOrder").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(1000d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOrder").value("18-11-2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusOrder").value("CONFIRMADO"));
    }

    @Test
    public void pagarPedidoByMesaIdMesa() throws Exception {

        this.mockMvc.perform(put("/order/pagarPedidoByMesaIdMesa/1").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idOrder").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(1000d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOrder").value("18-11-2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusOrder").value("PAGADO"));
    }




}
