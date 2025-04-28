package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.User;
import com.restaurant.restaurantApi.model.PrimaryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        User user = usuarioServiceImpl.getByNombreUsuario(nombreUsuario).get();
        return PrimaryUser.build(user);
    }


}
