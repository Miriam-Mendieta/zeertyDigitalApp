package com.miriammendieta.zeerty_digital_app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.service.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioService usuarioService;

    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/home")
    public String homeAdmin(Model model, Principal principal, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        Usuario usuario = usuarioService.buscarPorEmail(principal.getName());
        model.addAttribute("usuario", usuario);

        return "admin/home";
    }

    // LISTA DE USUARIOS
    @GetMapping("/lista")
    public String listaUsuarios(Model model, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "lista_usuarios";
    }

    // FORMULARIO CREAR USUARIO (GET)
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        model.addAttribute("usuario", new Usuario());
        return "crear_usuario";
    }

    // CREAR USUARIO (POST)
    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttrs) {
        if (usuarioService.emailExiste(usuario.getEmail())) {
            redirectAttrs.addFlashAttribute("mensaje", "El email ya está registrado");
            redirectAttrs.addFlashAttribute("tipo", "warning");
            return "redirect:/admin/crear";
        }
        usuarioService.crearUsuario(
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getPasswordHash(),
                usuario.getTelefono(),
                usuario.getRol(),
                usuario.getEstado());
        redirectAttrs.addFlashAttribute("mensaje", "Usuario creado correctamente");
        redirectAttrs.addFlashAttribute("tipo", "success");

        return "redirect:/admin/lista";
    }

    // MODIFICAR USUARIO (GET)
    @GetMapping("/modificar/{id}")
    public String mostrarModificar(@PathVariable Integer id, Model model, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "modificar_usuario";
    }

    // MODIFICAR USUARIO (POST)
    @PostMapping("/modificar")
    public String modificarUsuario(@ModelAttribute Usuario usuario,
            RedirectAttributes redirectAttrs) {

        try {
            usuarioService.modificarUsuario(usuario);
            redirectAttrs.addFlashAttribute("mensaje", "Usuario modificado correctamente");
            redirectAttrs.addFlashAttribute("tipo", "success");

        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("mensaje", e.getMessage());
            redirectAttrs.addFlashAttribute("tipo", "warning");

            return "redirect:/admin/modificar/" + usuario.getIdUsuario();
        }

        return "redirect:/admin/lista";
    }

    // ELIMINAR USUARIO
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttrs) {

        usuarioService.eliminarUsuario(id);

        redirectAttrs.addFlashAttribute("mensaje", "Usuario eliminado correctamente");
        redirectAttrs.addFlashAttribute("tipo", "warning");

        return "redirect:/admin/lista";
    }

    // DESACTIVAR USUARIO
    @GetMapping("/desactivar/{id}")
    public String desactivarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttrs) {

        usuarioService.desactivarUsuario(id);

        redirectAttrs.addFlashAttribute("mensaje", "Usuario desactivado");
        redirectAttrs.addFlashAttribute("tipo", "info");

        return "redirect:/admin/lista";
    }

    // ACTIVAR USUARIO
    @GetMapping("/activar/{id}")
    public String activarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttrs) {

        usuarioService.activarUsuario(id);

        redirectAttrs.addFlashAttribute("mensaje", "Usuario activado");
        redirectAttrs.addFlashAttribute("tipo", "success");

        return "redirect:/admin/lista";
    }
}
