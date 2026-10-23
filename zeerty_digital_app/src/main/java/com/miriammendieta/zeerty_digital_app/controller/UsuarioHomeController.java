package com.miriammendieta.zeerty_digital_app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.service.ActivoDigitalService;
import com.miriammendieta.zeerty_digital_app.service.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsuarioHomeController {

    private final UsuarioService usuarioService;
    private final ActivoDigitalService activoDigitalService;

    public UsuarioHomeController(UsuarioService usuarioService,
                                 ActivoDigitalService activoDigitalService) {
        this.usuarioService = usuarioService;
        this.activoDigitalService = activoDigitalService;
    }

    @GetMapping("/home")
    public String homeUsuario(Model model, Principal principal, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        Usuario usuario = usuarioService.buscarPorEmail(principal.getName());
        model.addAttribute("usuario", usuario);

        // CARGAR LISTA DE ACTIVOS
        model.addAttribute("listaActivos", activoDigitalService.listar());

        return "activos/home";
    }
}
