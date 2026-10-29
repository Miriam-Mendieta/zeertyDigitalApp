package com.miriammendieta.zeerty_digital_app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.model.Rol;
import com.miriammendieta.zeerty_digital_app.service.UsuarioService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/registro-movimientos")
public class AuditoriaController {

    private final UsuarioService usuarioService;

    public AuditoriaController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostConstruct
    public void init() {
        System.out.println(">>> AuditoriaController CARGADO");
    }

    @GetMapping("")
    public String verRegistro(Model model, Principal principal, HttpServletResponse response, HttpSession session) {

        // Evitar cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        // Obtener usuario desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
       

        // Si no está en sesión, obtenerlo por principal
        if (usuario == null && principal != null) {
            usuario = usuarioService.buscarPorEmail(principal.getName());
            session.setAttribute("usuario", usuario);
        }

        // Si sigue siendo null → expulsar
        if (usuario == null) {
            return "redirect:/login";
        }

        // Mostrar rol real en consola
        System.out.println(">>> ROL DEL USUARIO REAL: [" + usuario.getRol() + "]");

        // Restricción de acceso
        if (usuario.getRol() != Rol.ADMINISTRACION && usuario.getRol() != Rol.AUDITORIA) {
            return "redirect:/home?error=permiso";
        }

        model.addAttribute("usuario", usuario);
        return "registro-movimientos";
    }
}
