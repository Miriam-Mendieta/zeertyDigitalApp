package com.miriammendieta.zeerty_digital_app.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.service.UsuarioService;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioService usuarioService;

    public CustomSuccessHandler(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // Obtener email del usuario autenticado
        String email = authentication.getName();

        // Cargar el usuario completo desde la BD
        Usuario usuario = usuarioService.buscarPorEmail(email);

        // Guardarlo en la sesión
        request.getSession().setAttribute("usuario", usuario);

        // Redirección por rol
        if (usuario.getRol().name().equals("ADMINISTRACION")) {
            response.sendRedirect("/admin/home");
        } else if(usuario.getRol().name().equals("AUDITORIA")) {
            response.sendRedirect("/registro-movimientos");

        } else {
            response.sendRedirect("/home");
        }
    }
}

