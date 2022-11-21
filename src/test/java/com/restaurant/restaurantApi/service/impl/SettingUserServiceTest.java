package com.restaurant.restaurantApi.service.impl;


import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.model.SettingUser;
import com.restaurant.restaurantApi.service.inter.ISettingUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class SettingUserServiceTest {
    @Autowired
    private ISettingUserService settingUserService;




    @Test
    public void guardar_configuracion_de_usuario(){
        SettingUser settingUser = new SettingUser();
        settingUser.setCantMesas(10);
        settingUser.setNombreUsuario("user123");
        SettingUser settingUsersave = settingUserService.saveSettingUser(settingUser);
        assertNotNull(settingUsersave.getIdSettingUser());
        assertEquals(settingUsersave.getNombreUsuario(),"user123");
        assertEquals(settingUsersave.getCantMesas(),10);

    }
    @Test
    public void buscar_cofiguracion_de_usuario_por_nombre_usuario(){
        SettingUser settingUser = new SettingUser();
        settingUser.setCantMesas(15);
        settingUser.setNombreUsuario("usertest");
        settingUserService.saveSettingUser(settingUser);
        SettingUser settingUser1 = settingUserService.findByUsername("usertest");
        assertNotNull(settingUser1.getIdSettingUser());
        assertEquals(settingUser1.getNombreUsuario(),"usertest");
        assertEquals(settingUser1.getCantMesas(),15);

    }



}
