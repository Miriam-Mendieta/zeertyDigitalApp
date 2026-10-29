package com.miriammendieta.zeerty_digital_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.miriammendieta.zeerty_digital_app.model.Rol;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

/**
 * Controlador encargado de gestionar el formulario de login.
 * se usa inyección por constructor.
 */

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(Model model,
            @RequestParam(required = false) String inactivo,
            @RequestParam(required = false) String error) {

        if (inactivo != null) {
            model.addAttribute("mensajeError", "Usuario inactivo. Contacte con el administrador.");
        }

        if (error != null) {
            model.addAttribute("mensajeError", "Usuario o contraseña incorrectos.");
        }

        return "login";
    }

    @PostMapping("/procesar-login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        Usuario u = usuarioRepository.findByEmail(email).orElse(null);

        if (u == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "login";
        }

        boolean ok = passwordEncoder.matches(password, u.getPasswordHash());

        if (!ok) {
            model.addAttribute("error", "Contraseña incorrecta");
            return "login";
        }

        // GUARDAR USUARIO EN SESIÓN
        session.setAttribute("usuario", u);

        Rol rol = u.getRol();

        switch (rol) {
            case ADMINISTRACION:
                return "redirect:/admin/home";

            case AUDITORIA:
                return "redirect:/registro-movimientos";

            case USUARIO_APP:
            case INVITADO:
                return "redirect:/activos/home";

            default:
                return "redirect:/login";
        }
    }
}
