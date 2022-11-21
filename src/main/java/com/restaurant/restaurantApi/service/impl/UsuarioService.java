package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.SettingUser;
import com.restaurant.restaurantApi.model.Usuario;
import com.restaurant.restaurantApi.repo.UsuarioRepository;
import com.restaurant.restaurantApi.service.inter.ISettingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private ISettingUserService iSettingUserService;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario save(Usuario usuario){
        Usuario usuario1 = usuarioRepository.save(usuario);
        SettingUser settingUser = new SettingUser();
        settingUser.setNombreUsuario(usuario.getNombreUsuario());
        settingUser.setCantMesas(10);
        this.iSettingUserService.saveSettingUser(settingUser);
        return usuario1;
    }

}
