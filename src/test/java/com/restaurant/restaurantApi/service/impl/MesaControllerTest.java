package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.enums.RolNombre;
import com.restaurant.restaurantApi.jwt.JwtEntryPoint;
import com.restaurant.restaurantApi.jwt.JwtProvider;
import com.restaurant.restaurantApi.jwt.JwtTokenFilter;
import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Rol;
import com.restaurant.restaurantApi.model.Usuario;
import com.restaurant.restaurantApi.model.UsuarioPrincipal;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;

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
    private WebApplicationContext wac;

    @Autowired
    private JwtProvider jwtProvider;


    private String token;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void saveMesa() throws Exception {
        when(this.iMesaService.saveMesa(Mockito.any(Mesa.class))).thenReturn(Mockito.any(Mesa.class));
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
        this.mockMvc.perform(post("/mesa/saveMesa").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findAllMesas() throws Exception {

        when(this.iMesaService.findAllMesas()).thenReturn(List.of(new Mesa()));
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
        this.mockMvc.perform(get("/mesa/findAllMesas").header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)));
    }
}
