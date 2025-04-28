package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import com.restaurant.restaurantApi.service.inter.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class MesaServiceTest {

    @Autowired
    private IMesaService iMesaService;

    @Autowired
    private IProductService productService;


    @Test
    public void guardar_mesa() {
        Mesa mesa = new Mesa();
        mesa.setPositionMesa(1);
        mesa.setEstadoMesa("Ocupada");
        mesa.setNumeroMesa(2);
        Mesa mesaSave = this.iMesaService.saveMesa(mesa);
        assertNotNull(mesaSave.getIdMesa());
        assertEquals("Ocupada", mesaSave.getEstadoMesa());
        assertEquals(1, mesaSave.getPositionMesa());
        assertEquals(2, mesaSave.getNumeroMesa());

    }

    @Test
    public void buscar_mesa_por_id() {
        Mesa mesa = new Mesa();
        mesa.setPositionMesa(40);
        mesa.setEstadoMesa("Disponible");
        mesa.setNumeroMesa(45);
        this.iMesaService.saveMesa(mesa);
        Mesa mesaEncontrada = this.iMesaService.getMesaById(5);
        assertNotNull(mesaEncontrada.getIdMesa());
        assertEquals("Disponible", mesaEncontrada.getEstadoMesa());
        assertEquals(40, mesaEncontrada.getPositionMesa());
        assertEquals(45, mesaEncontrada.getNumeroMesa());
    }

    @Test
    public void buscar_todas_las_mesas() {
        List<Mesa> mesaList = this.iMesaService.findAllMesas();
        assertEquals(4, mesaList.size());
        assertEquals("No Disponible", mesaList.get(0).getEstadoMesa());
        assertEquals(1, mesaList.get(0).getPositionMesa());
        assertEquals(1, mesaList.get(0).getNumeroMesa());

    }

    @Test
    public void agregar_pedido_a_mesa_por_id(){
        Order order = new Order();
        order.setStatusOrder("CONFIRMADO");
        order.setDateOrder("21-11-2022");
        order.setTotalPrice(100d);
        order.setProducts(List.of(this.productService.filterProductByName("papas").get(0)));
        Mesa mesaConPedido = this.iMesaService.addOrderByIdMesa(1,order);
        assertNotNull(mesaConPedido.getIdMesa());
        assertEquals("No Disponible", mesaConPedido.getEstadoMesa());
        assertEquals(1, mesaConPedido.getNumeroMesa());
        assertEquals(1, mesaConPedido.getPositionMesa());
        assertEquals(4, mesaConPedido.getListPedidos().size());
        assertEquals("Papas Fritas", mesaConPedido.getListPedidos().get(0).getProducts().get(0).getName());
    }

    @Test
    public void cambiar_estado_de_mesa_por_id() {
        Mesa mesa = new Mesa();
        mesa.setPositionMesa(8);
        mesa.setEstadoMesa("Reservada");
        mesa.setNumeroMesa(8);
        Mesa mesaSave = this.iMesaService.saveMesa(mesa);
        Mesa mesaConEstadoCambiado = this.iMesaService.changeEstadoMesa(mesaSave.getIdMesa(), "Disponible");
        assertNotNull(mesaConEstadoCambiado.getIdMesa());
        assertEquals("Disponible", mesaConEstadoCambiado.getEstadoMesa());
        assertEquals(8, mesaConEstadoCambiado.getPositionMesa());
        assertEquals(8, mesaConEstadoCambiado.getNumeroMesa());
    }


    @Test
    public void borrar_mesa_por_id(){
        Mesa mesa = new Mesa();
        mesa.setPositionMesa(8);
        mesa.setEstadoMesa("Reservada");
        mesa.setNumeroMesa(8);
        Mesa mesaSave = this.iMesaService.saveMesa(mesa);
        this.iMesaService.deleteMesaById(mesaSave.getIdMesa());
        Mesa mesa1 = this.iMesaService.getMesaById(mesaSave.getIdMesa());
        assertNull(mesa1);
    }

    @Test
    public void update_mesa_por_id() {
        Mesa mesa = new Mesa();
        mesa.setPositionMesa(8);
        mesa.setEstadoMesa("Reservada");
        mesa.setNumeroMesa(8);
        Mesa mesaSave = this.iMesaService.saveMesa(mesa);
        Mesa mesaUpdate = this.iMesaService.updatePositionMesa(mesaSave.getIdMesa(), 90);
        assertNotNull(mesaUpdate.getIdMesa());
        assertEquals("Reservada", mesaUpdate.getEstadoMesa());
        assertEquals(90, mesaUpdate.getPositionMesa());
        assertEquals(8, mesaUpdate.getNumeroMesa());
    }

    @Test
    public void juntar_mesas(){
        Mesa mesa = new Mesa();
        mesa.setPositionMesa(9);
        mesa.setEstadoMesa("Disponible");
        mesa.setNumeroMesa(9);
        Mesa mesa1 = new Mesa();
        mesa1.setPositionMesa(10);
        mesa1.setEstadoMesa("Disponible");
        mesa1.setNumeroMesa(10);
        Mesa mesa1Guardada = this.iMesaService.saveMesa(mesa);
        Mesa mesa2Guardada = this.iMesaService.saveMesa(mesa1);
        Mesa mesaJuntas = this.iMesaService.juntarMesas(mesa1Guardada.getIdMesa(), mesa2Guardada.getIdMesa());
        assertNotNull(mesaJuntas.getIdMesa());
        assertEquals("Disponible", mesaJuntas.getEstadoMesa());
        assertEquals(9, mesaJuntas.getPositionMesa());
        assertEquals(9, mesaJuntas.getNumeroMesa());

    }
}
