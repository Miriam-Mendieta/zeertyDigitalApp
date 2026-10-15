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
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/procesar-login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            Model model) {
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

        Rol rol = u.getRol();

        switch (rol) {
            case ADMINISTRACION:
                return "redirect:/admin/home";

            case AUDITORIA:
                return "redirect:/auditoria/home";

            case USUARIO_APP:
                return "redirect:/home";

            case INVITADO:
                return "redirect:/home";

            default:
                return "redirect:/login";
        }

    }
}
