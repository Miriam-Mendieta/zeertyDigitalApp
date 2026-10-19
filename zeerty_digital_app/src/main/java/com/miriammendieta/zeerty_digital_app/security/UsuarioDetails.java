package com.miriammendieta.zeerty_digital_app.security;

import com.miriammendieta.zeerty_digital_app.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Clase que adapta la entidad Usuario al modelo de seguridad de Spring Security.
 * Spring Security necesita un objeto UserDetails para autenticar y validar permisos.
 * Aquí definimos cómo se obtiene el email, contraseña, rol y estado (activo/inactivo).
 */
public class UsuarioDetails implements UserDetails {

    private final Usuario usuario;

    /**
     * Constructor que recibe el usuario real de la base de datos.
     */
    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve la lista de roles del usuario.
     * Spring Security exige una colección de GrantedAuthority.
     * En este caso solo hay un rol, así que devolvemos una lista con un único elemento.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()));
    }

    /**
     * Devuelve la contraseña encriptada del usuario.
     */
    @Override
    public String getPassword() {
        return usuario.getPasswordHash();
    }

    /**
     * Devuelve el email, que es el identificador del usuario.
     */
    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    /**
     * Indica si el usuario está habilitado para iniciar sesión.
     * Si está INACTIVO, Spring Security bloqueará el login automáticamente.
     */
    @Override
    public boolean isEnabled() {
        return usuario.getEstado().name().equals("ACTIVO");
    }

    // Métodos mínimos obligatorios para que Spring Security funcione correctamente.
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
}
