package com.restaurant.restaurantApi.service.impl.controller;

import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.model.Rol;
import com.restaurant.restaurantApi.model.Usuario;
import com.restaurant.restaurantApi.service.impl.RolService;
import com.restaurant.restaurantApi.service.impl.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    @Autowired
    UsuarioService usuarioService;

    @BeforeEach
    public void setUp(){
        Usuario usuario = new Usuario("userTest", "userTest123","usertest@gmail.com",
                passwordEncoder.encode("12345"));
        Rol rol = new Rol(RolNombre.ROLE_ADMIN) ;
        usuario.setRol(rol);
        usuarioService.save(usuario);
    }



    @Test
    public void guardar_usuario_nuevo() throws Exception {
        this.mockMvc.perform(post("/auth/nuevo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"usuarioPrueba\",\"nombreUsuario\":\"usuarioPrueba123\",\"email\":\"usuarioPrueba@gmail.com\"," +
                                "\"password\":\"admin\",\"roles\":[\"ROLE_ADMIN\"]}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("usuario guardado"));
    }

    @Test
    public void bad_request_campos_invalidos_al_intentar_guardar_un_nuevo_usuario() throws Exception {

        this.mockMvc.perform(post("/auth/nuevo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"errorNombre\":\"usuarioPrueba\",\"nombreUsuario\":\"userTest123\",\"email\":\"usuarioPrueba@gmail.com\"," +
                                "\"password\":\"admin\",\"roles\":[\"ROLE_ADMIN\"]}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Error, campos invalidos"));
    }

    @Test
    public void error_al_email_ya_existente_al_intentar_crear_un_nuevo_usuario() throws Exception {
        this.mockMvc.perform(post("/auth/nuevo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"usuarioPrueba\",\"nombreUsuario\":\"usuarioPrueba123\",\"email\":\"usertest@gmail.com\"," +
                                "\"password\":\"admin\",\"roles\":[\"ROLE_ADMIN\"]}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Error, ese email ya existe"));
    }

    @Test
    public void error_al_username_ya_existente_al_intentar_crear_un_nuevo_usuario() throws Exception {
        this.mockMvc.perform(post("/auth/nuevo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"usuarioPrueba\",\"nombreUsuario\":\"userTest123\",\"email\":\"usuarioPrueba@gmail.com\"," +
                                "\"password\":\"admin\",\"roles\":[\"ROLE_ADMIN\"]}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Error, ese username ya existe"));
    }


    @Test
    public void login() throws Exception {
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreUsuario\":\"userTest123\",\"password\":\"12345\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bearer").value("Bearer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombreUsuario").value("userTest123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rol").value("ROLE_ADMIN"));

    }

    @Test
    public void bad_request_campos_invalidos_al_intentar_loguearse() throws Exception {
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ErrornombreUsuario\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje").value("Error, campos invalidos"));

    }
}
