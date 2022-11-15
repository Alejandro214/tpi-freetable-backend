package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.controller.MesaController;
import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;




@SpringBootTest
@AutoConfigureMockMvc
public class MesaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMesaService iMesaService;

    @Test
    public void saveMesa() throws Exception {
        when(this.iMesaService.saveMesa(new Mesa())).thenReturn(new Mesa());
        this.mockMvc.perform(get("/mesa/saveMesa")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }
}
