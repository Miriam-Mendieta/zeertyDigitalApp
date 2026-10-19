package com.miriammendieta.zeerty_digital_app.config;

import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.security.UsuarioDetails;
import com.miriammendieta.zeerty_digital_app.service.UsuarioService;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    /**
     * Servicio que carga usuarios desde la base de datos para autenticación.
     */
    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Método que busca un usuario por email y lo adapta a UserDetails.
     * Aquí también validamos si el usuario está activo o inactivo.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Buscar usuario por email
        Usuario usuario = usuarioService.buscarPorEmail(email);

        // Si no existe, lanzar excepción estándar
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // Si el usuario está inactivo, bloquear login con mensaje claro
        if (!usuario.getEstado().name().equals("ACTIVO")) {
            throw new DisabledException("Usuario inactivo. Contacte con el administrador/a.");
        }

        // Devolver nuestro UserDetails personalizado
        return new UsuarioDetails(usuario);
    }
}
