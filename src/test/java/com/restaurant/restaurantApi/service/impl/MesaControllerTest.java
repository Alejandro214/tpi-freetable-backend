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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class MesaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMesaService iMesaService;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private JwtProvider jwtProvider;

    private String token;


    @BeforeEach
    public void setUp() {
        UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal("Alan","Alan123",
                "alan@gamil.com","123", new Rol(RolNombre.ROLE_ADMIN));
        Usuario usuario = new Usuario();
        usuario.setNombre("Alan");
        usuario.setNombreUsuario("Alan123");
        usuario.setEmail("alan@gamil.com");
        usuario.setPassword("123");
        usuario.setRol(new Rol(RolNombre.ROLE_ADMIN));
        when(usuarioService.getByNombreUsuario("Alan123")).thenReturn(Optional.of(usuario));
        this.token = this.jwtProvider.generateTokenByUserPrincipal(usuarioPrincipal);
    }

    @Test
    public void saveMesa() throws Exception {
        when(this.iMesaService.saveMesa(Mockito.any(Mesa.class))).thenReturn(Mockito.any(Mesa.class));
        this.mockMvc.perform(post("/mesa/saveMesa").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMesa\":1,\"listPedidos\":[],\"estadoMesa\":\"Disponible\"," +
                                "\"positionMesa\":2,\"numeroMesa\":5}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getMesaById() throws Exception {
        Mesa mesa = new Mesa();
        mesa.setIdMesa(1);
        mesa.setNumeroMesa(24);
        mesa.setPositionMesa(3);
        mesa.setEstadoMesa("Ocupada");
        when(this.iMesaService.getMesaById(1)).thenReturn(mesa);
        this.mockMvc.perform(get("/mesa/getMesaById/1").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Ocupada"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(24))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(3));
    }

    @Test
    public void findAllMesas() throws Exception {
        when(this.iMesaService.findAllMesas()).thenReturn(List.of(new Mesa()));
        this.mockMvc.perform(get("/mesa/findAllMesas").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)));
    }


    @Test
    public void addOrderMesa() throws Exception {
        Mesa mesa = new Mesa();
        mesa.setIdMesa(2);
        mesa.setNumeroMesa(25);
        mesa.setPositionMesa(4);
        mesa.setEstadoMesa("Ocupada");
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setMesa(mesa);
        order.setIdOrder(1);
        order.setStatusOrder("CONFIRMADO");
        order.setTotalPrice(100d);
        order.setDateOrder("17-11-2022");
        orders.add(order);
        mesa.setListPedidos(orders);
        when(this.iMesaService.addOrderByIdMesa(Mockito.any(Integer.class),Mockito.any(Order.class))).thenReturn(mesa);
        this.mockMvc.perform(put("/mesa/addOrderMesa/2").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idOrder\":1,\"products\":[],\"totalPrice\":100.0, " +
                                "\"dateOrder\":\"17-11-2022\",\"statusOrder\":\"PAGADO\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Ocupada"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(25))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listPedidos[0].idOrder").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listPedidos.[0].totalPrice").value(100d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listPedidos.[0].dateOrder").value("17-11-2022"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listPedidos.[0].statusOrder").value("CONFIRMADO"));
    }

    @Test
    public void changeEstadoMesa() throws Exception {
        Mesa mesa = new Mesa();
        mesa.setIdMesa(2);
        mesa.setNumeroMesa(25);
        mesa.setPositionMesa(4);
        mesa.setEstadoMesa("No disponible");
        Mesa mesaUpdate = mesa;
        mesaUpdate.setEstadoMesa("Disponible");
        when(this.iMesaService.getMesaById(Mockito.any(Integer.class))).thenReturn(mesa);
        when(this.iMesaService.changeEstadoMesa(Mockito.any(Integer.class),Mockito.any(String.class))).thenReturn(mesaUpdate);
        this.mockMvc.perform(put("/mesa/changeEstadoMensa/2/Disponible").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMesa\":2,\"listPedidos\":[],\"estadoMesa\":\"No Disponible\"," +
                                "\"positionMesa\":4,\"numeroMesa\":25}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("Disponible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(25))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(4));
    }

    @Test
    public void deleteMesaById() throws Exception {
        this.mockMvc.perform(delete("/mesa/deleteMesaById/1").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updatePositionMesa() throws Exception {
        Mesa mesa = new Mesa();
        mesa.setIdMesa(2);
        mesa.setNumeroMesa(25);
        mesa.setPositionMesa(4);
        mesa.setEstadoMesa("No disponible");
        //mesa -> mesaUpdate
        Mesa mesaUpdate = mesa;
        mesaUpdate.setPositionMesa(10);
        when(this.iMesaService.getMesaById(Mockito.any(Integer.class))).thenReturn(mesa);
        when(this.iMesaService.updatePositionMesa(Mockito.any(Integer.class),Mockito.any(Integer.class))).thenReturn(mesaUpdate);
        this.mockMvc.perform(put("/mesa/updatePositionMesa/2/10").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idMesa\":2,\"listPedidos\":[],\"estadoMesa\":\"No Disponible\"," +
                                "\"positionMesa\":4,\"numeroMesa\":25}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idMesa").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoMesa").value("No disponible"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroMesa").value(25))
                .andExpect(MockMvcResultMatchers.jsonPath("$.positionMesa").value(10));
    }
}
