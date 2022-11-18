package com.restaurant.restaurantApi.service.impl;
import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.jwt.JwtProvider;
import com.restaurant.restaurantApi.model.*;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class MesaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtProvider jwtProvider;

    private String token;


    @BeforeEach
    public void setUp() {
    }

    @Test
    public void saveMesa() throws Exception {
        this.mockMvc.perform(post("/mesa/saveMesa").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMesa\":10,\"listPedidos\":[],\"estadoMesa\":\"Disponible\"," +
                                "\"positionMesa\":20,\"numeroMesa\":80}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getMesaById() throws Exception {
        this.mockMvc.perform(get("/mesa/getMesaById/1").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("No disponible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(1));
    }

    @Test
    public void findAllMesas() throws Exception {
        this.mockMvc.perform(get("/mesa/findAllMesas").header("Authorization", "Bearer " + "token"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(4)));
    }


    @Test
    public void addOrderMesa() throws Exception {

        this.mockMvc.perform(put("/mesa/addOrderMesa/2").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idOrder\":1,\"products\":[],\"totalPrice\":100.0, " +
                                "\"dateOrder\":\"17-11-2022\",\"statusOrder\":\"CONFIRMADO\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Reservada"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listPedidos[0].idOrder").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listPedidos.[0].totalPrice").value(100d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listPedidos.[0].dateOrder").value("17-11-2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listPedidos.[0].statusOrder").value("CONFIRMADO"));
    }

    @Test
    public void changeEstadoMesa() throws Exception {

        this.mockMvc.perform(put("/mesa/changeEstadoMensa/2/Disponible").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMesa\":2,\"listPedidos\":[],\"estadoMesa\":\"No Disponible\"," +
                                "\"positionMesa\":4,\"numeroMesa\":25}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Disponible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(2));
    }

    @Test
    public void deleteMesaById() throws Exception {
        this.mockMvc.perform(delete("/mesa/deleteMesaById/1").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updatePositionMesa() throws Exception {

        this.mockMvc.perform(put("/mesa/updatePositionMesa/2/10").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                 /*       .content("{\"idMesa\":2,\"listPedidos\":[],\"estadoMesa\":\"No Disponible\"," +
                                "\"positionMesa\":4,\"numeroMesa\":25}"))*/
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Reservada"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(10));
    }
}
