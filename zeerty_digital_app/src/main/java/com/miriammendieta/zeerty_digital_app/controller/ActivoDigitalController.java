package com.miriammendieta.zeerty_digital_app.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.miriammendieta.zeerty_digital_app.model.ActivoDigital;
import com.miriammendieta.zeerty_digital_app.model.TipoAccion;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.service.ActivoDigitalService;
import com.miriammendieta.zeerty_digital_app.service.LogMovimientoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/activos")
public class ActivoDigitalController {

    private final ActivoDigitalService service;

    public ActivoDigitalController(ActivoDigitalService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("listaActivos", service.listar());

        return "activos/home";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("activo", new ActivoDigital());
        return "activos/crear-activo";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ActivoDigital activo, HttpSession session) {
        // Recuperar usuario logueado desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Asignar usuario al activo
        activo.setId_usuario(usuario.getIdUsuario());

        // Fecha de alta por defecto
        if (activo.getFecha_alta() == null) {
            activo.setFecha_alta(LocalDate.now());
        }

        // Guardar activo
        service.guardar(activo);

        // Registrar movimiento en LOG_MOVIMIENTOS
        LogMovimientoService.registrar(
                TipoAccion.CREAR,
                usuario.getIdUsuario(),
                activo.getId_activo());

        return "redirect:/activos/home";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        ActivoDigital activo = service.buscarPorId(id);
        model.addAttribute("activo", activo);
        return "activos/ver-activo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        ActivoDigital activo = service.buscarPorId(id);
        model.addAttribute("activo", activo);
        return "activos/editar-activo";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute ActivoDigital activo, HttpSession session) {

        // Recuperar usuario logueado
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Asignar SIEMPRE el usuario al activo
        activo.setId_usuario(usuario.getIdUsuario());

        // Si no hay fecha de alta, mantener la existente o poner hoy
        if (activo.getFecha_alta() == null) {
            activo.setFecha_alta(LocalDate.now());
        }

        service.guardar(activo);

        return "redirect:/activos/home";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/activos/home";
    }
}