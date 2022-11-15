package com.restaurant.restaurantApi.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UsuarioPrincipal implements UserDetails {
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String password;
    private Rol rol;

    public UsuarioPrincipal(String nombre, String nombreUsuario, String email, String password, Rol rol) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPassword(), usuario.getRol());
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

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Rol getRol() {
        return rol;
    }
}