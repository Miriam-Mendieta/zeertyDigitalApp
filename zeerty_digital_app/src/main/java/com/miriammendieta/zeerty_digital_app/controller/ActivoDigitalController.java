package com.miriammendieta.zeerty_digital_app.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.miriammendieta.zeerty_digital_app.model.ActivoDigital;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.service.ActivoDigitalService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/activos")
public class ActivoDigitalController {

    private final ActivoDigitalService service = new ActivoDigitalService();

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("listaActivos", service.listar());
        return "home"; // home.html
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("activo", new ActivoDigital());
        return "activos/crear-activo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ActivoDigital activo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Asignar el usuario real
        activo.setId_usuario(usuario.getIdUsuario());

        // Si no ponen fecha de alta, se pone hoy
        if (activo.getFecha_alta() == null) {
            activo.setFecha_alta(LocalDate.now());
        }

        service.guardar(activo);

        return "redirect:/activos/home";
    }

}
