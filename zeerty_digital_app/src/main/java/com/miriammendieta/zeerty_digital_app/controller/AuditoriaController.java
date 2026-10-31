package com.miriammendieta.zeerty_digital_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miriammendieta.zeerty_digital_app.DTO.LogMovimientoDTO;
import com.miriammendieta.zeerty_digital_app.model.ActivoDigital;
import com.miriammendieta.zeerty_digital_app.model.LogMovimiento;
import com.miriammendieta.zeerty_digital_app.model.Rol;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.repository.LogMovimientoRepository;
import com.miriammendieta.zeerty_digital_app.service.ActivoDigitalService;
import com.miriammendieta.zeerty_digital_app.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/registro-movimientos")
public class AuditoriaController {

    private final LogMovimientoRepository logRepo;
    private final UsuarioService usuarioService;
    private final ActivoDigitalService activoService;

    public AuditoriaController(LogMovimientoRepository logRepo,
            UsuarioService usuarioService,
            ActivoDigitalService activoService) {
        this.logRepo = logRepo;
        this.usuarioService = usuarioService;
        this.activoService = activoService;
    }

    @GetMapping("")
    public String verRegistro(Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        if (usuario.getRol() != Rol.ADMINISTRACION && usuario.getRol() != Rol.AUDITORIA) {
            return "redirect:/home?error=permiso";
        }

        // Cargar logs
        List<LogMovimiento> logs = logRepo.findAll();
        List<LogMovimientoDTO> movimientos = new ArrayList<>();

        for (LogMovimiento log : logs) {

            LogMovimientoDTO dto = new LogMovimientoDTO();

            dto.setId(log.getIdMovimiento());
            dto.setFechaHora(log.getFechaHora());
            dto.setAccion(log.getTipoAccion().name());

            // Usuario
            Usuario u = usuarioService.buscarPorId(log.getIdUsuario());
            dto.setUsuario(u.getNombre() + " " + u.getApellidos());
            dto.setRolUsuario(u.getRol().name());

            // Activo (manejar activos eliminados)
            Integer idActivo = log.getIdActivo();

            if (idActivo != null) {
                ActivoDigital a = activoService.buscarPorId(idActivo);
                dto.setActivoNombre(a.getNombre());
            } else {
                dto.setActivoNombre("Activo eliminado");
            }

            movimientos.add(dto);
        }

        model.addAttribute("movimientos", movimientos);
        model.addAttribute("usuario", usuario);

        return "registro-movimientos";

    }
}
