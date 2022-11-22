package com.restaurant.restaurantApi.controller;
import com.restaurant.restaurantApi.jwt.JwtProvider;
import org.hamcrest.Matchers;
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
public class MesaControllerTest {

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
    public void guardar_mesa() throws Exception {
        this.mockMvc.perform(post("/mesa/saveMesa").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMesa\":10,\"listPedidos\":[],\"estadoMesa\":\"Disponible\"," +
                                "\"positionMesa\":20,\"numeroMesa\":80}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Disponible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(80))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(20));;
    }

    @Test
    public void bad_request_campos_invalidos_al_intentar_guardar_una_mesa() throws Exception {
        this.mockMvc.perform(post("/mesa/saveMesa").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"listPedidos\":[],\"estadoMesa\":\"Disponible\"," +
                                "\"positionMesa\":20,\"ErrornumeroMesa\":80}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Error, campos invalidos"));
    }

    @Test
    public void error_token_no_enviado_al_intentar_guardar_una_mesa() throws Exception {
        this.mockMvc.perform(post("/mesa/saveMesa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"listPedidos\":[],\"estadoMesa\":\"Disponible\"," +
                                "\"positionMesa\":20,\"numeroMesa\":80}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void get_mesa_by_id() throws Exception {
        this.mockMvc.perform(get("/mesa/getMesaById/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("No Disponible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(1));
    }

    @Test
    public void error_token_no_enviado_al_intentar_buscar_una_por_id() throws Exception {
        this.mockMvc.perform(get("/mesa/getMesaById/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void find_all_mesas() throws Exception {
        this.mockMvc.perform(get("/mesa/findAllMesas").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(4)));
    }

    @Test
    public void error_token_no_enviado_al_intentar_buscar_todas_las_mesas() throws Exception {
        this.mockMvc.perform(get("/mesa/findAllMesas"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    @Test
    public void agregar_un_pedido_a_una_mesa() throws Exception {
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
    public void bad_request_campos_invalidos_al_intentar_agregar_un_pedido_a_una_mesa() throws Exception {
        this.mockMvc.perform(put("/mesa/addOrderMesa/2").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idOrder\":1,\"products\":[],\"ErrortotalPrice\":100.0, " +
                                "\"dateOrder\":\"17-11-2022\",\"statusOrder\":\"CONFIRMADO\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Error, campos invalidos"));
    }

    @Test
    public void error_token_no_enviado_al_intentar_agregar_un_pedido_a_una_mesa() throws Exception {
        this.mockMvc.perform(put("/mesa/addOrderMesa/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idOrder\":1,\"products\":[],\"totalPrice\":100.0, " +
                                "\"dateOrder\":\"17-11-2022\",\"statusOrder\":\"CONFIRMADO\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }



    @Test
    public void cambiar_estado_de_una_mesa() throws Exception {
        this.mockMvc.perform(put("/mesa/changeEstadoMensa/2/Disponible").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Disponible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(2));
    }
/*
    @Test
    public void ErrorAlIntentarCambiarDestadoUnaMesaEstadoInvilado() throws Exception {
        this.mockMvc.perform(put("/mesa/changeEstadoMensa/2/pepe").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Disponible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(2));
    }*/

    @Test
    public void error_token_no_enviado_al_intentar_cambiar_de_estado_una_mesa() throws Exception {
        this.mockMvc.perform(put("/mesa/changeEstadoMensa/2/Disponible"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void borrar_una_mesa_por_id() throws Exception {
        this.mockMvc.perform(delete("/mesa/deleteMesaById/4").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



    @Test
    public void error_token_no_enviado_al_intentar_eliminar_una_mesa_por_id() throws Exception {
        this.mockMvc.perform(delete("/mesa/deleteMesaById/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    @Test
    public void update_posicion_mesa_por_id() throws Exception {
        this.mockMvc.perform(put("/mesa/updatePositionMesa/2/10").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Reservada"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(10));
    }


    @Test
    public void error_token_no_enviado_al_updatear_la_posicion_de_una_mesa_por_id() throws Exception {
        this.mockMvc.perform(put("/mesa/updatePositionMesa/2/10"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
