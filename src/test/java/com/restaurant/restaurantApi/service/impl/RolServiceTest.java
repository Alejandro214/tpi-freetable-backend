package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.service.inter.RolService;
import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.model.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class RolServiceTest {

    @Autowired
    private RolService rolService;


    @Test
    public void guardar_rol(){
        Rol rol = new Rol();
        rol.setRolNombre(RolNombre.ROLE_ADMIN);
        Rol rolSave = rolService.save(rol);
        assertNotNull(rolSave.getId());
        assertEquals(rolSave.getRolNombre(),RolNombre.ROLE_ADMIN);
    }
    @Test
    public void buscar_rol_por_rol_nombre(){
        Rol rolEncontrado = this.rolService.getByRolNombre(RolNombre.ROLE_SOPORTE).get();
        assertNotNull(rolEncontrado.getId());
        assertEquals(rolEncontrado.getRolNombre(),RolNombre.ROLE_SOPORTE);
    }
}
