package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.repo.IMesaRepo;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


class MesaServiceImplTest {

    @Mock
    private IMesaRepo mesaRepo;

    @InjectMocks
    private MesaServiceImpl mesaService;

    private Mesa mesa = new Mesa();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mesa.setIdMesa(1);
    }

    @Test
    void saveMesa() {
        when(this.mesaRepo.save(any(Mesa.class))).thenReturn(mesa);
        Mesa mesa = this.mesaService.saveMesa(this.mesa);
        assertEquals(1,mesa.getIdMesa());
        assertNotNull(mesa.getListPedidos());
    }

    @Test
    void getMesaById() {
        when(this.mesaRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(mesa));
        Mesa mesa = this.mesaService.getMesaById(1);
        assertEquals(1,mesa.getIdMesa());
        assertNotNull(mesa.getListPedidos());
    }
    @Test
    void findAllMesas() {
        when(this.mesaRepo.findAll()).thenReturn(asList(mesa));
        List<Mesa> mesas = this.mesaService.findAllMesas();
        assertEquals(1,mesas.size());
        Mesa mesa1 = mesas.get(0);
        assertEquals(1,mesa1.getIdMesa());
        assertNotNull(mesa1.getListPedidos());
    }
}