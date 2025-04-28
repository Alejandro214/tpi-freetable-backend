package com.restaurant.restaurantApi.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class PrimaryUser implements UserDetails {
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String password;
    private Rol rol;

    public PrimaryUser(String nombre, String nombreUsuario, String email, String password, Rol rol) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public static PrimaryUser build(User user){
        return new PrimaryUser(user.getNombre(), user.getNombreUsuario(), user.getEmail(), user.getPassword(), user.getRol());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Rol getRol() {
        return rol;
    }
}