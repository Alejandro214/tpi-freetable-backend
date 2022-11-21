package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.service.inter.IMesaService;
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


 /*   @Test
    public void borrar_mesa_por_id(){
        Mesa mesa = new Mesa();
        mesa.setPositionMesa(8);
        mesa.setEstadoMesa("Reservada");
        mesa.setNumeroMesa(8);

        Mesa mesaSave = this.iMesaService.saveMesa(mesa);
        this.iMesaService.deleteMesaById(mesaSave.getIdMesa());
        Mesa mesa1 = this.iMesaService.getMesaById(mesaSave.getIdMesa());
        assertNull(mesa1);
    }*/

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
}
