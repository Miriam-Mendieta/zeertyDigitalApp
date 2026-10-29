package com.miriammendieta.zeerty_digital_app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.model.Rol;
import com.miriammendieta.zeerty_digital_app.service.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/registro-movimientos")
public class RegistroMovimientosController {

    private final UsuarioService usuarioService;

    public RegistroMovimientosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String verRegistro(Model model, Principal principal, HttpServletResponse response) {

        // Evitar cache (igual que en admin)
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        // Obtener usuario logueado
        Usuario usuario = usuarioService.buscarPorEmail(principal.getName());
        model.addAttribute("usuario", usuario);

        // RESTRICCIÓN DE ACCESO
        if (usuario.getRol() != Rol.ADMINISTRACION && usuario.getRol() != Rol.AUDITORIA) {
            return "redirect:/home?error=permiso";
        }

        return "registro-movimientos";
    }
}
