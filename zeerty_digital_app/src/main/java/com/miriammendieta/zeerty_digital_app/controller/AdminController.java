package com.miriammendieta.zeerty_digital_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.service.UsuarioService;


@Controller
@RequestMapping("/admin")
public class AdminController {
  
   private final UsuarioService usuarioService;

public AdminController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

    // HOME DEL ADMIN
    @GetMapping("/home")
    public String homeAdmin(Model model) {
      model.addAttribute("admin", "Miriam"); // luego lo haremos dinámico
      return "admin_home";
    }

    // LISTA DE USUARIOS
    @GetMapping("/lista")
    public String listaUsuarios(Model model) {
      model.addAttribute("usuarios", usuarioService.listarTodos());
      return "lista_usuarios";
    }

    // FORMULARIO CREAR USUARIO (GET)
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
      model.addAttribute("usuario", new Usuario());
      return "crear_usuario";
    }

    // CREAR USUARIO (POST)
    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
      usuarioService.crearUsuario(
          usuario.getNombre(),
          usuario.getApellidos(),
          usuario.getEmail(),
          usuario.getPasswordHash(),
          usuario.getTelefono(),
          usuario.getRol(),
          usuario.getEstado());
      return "redirect:/admin/lista";
    }

    // MODIFICAR USUARIO (GET)
    @GetMapping("/modificar/{id}")
    public String mostrarModificar(@PathVariable Integer id, Model model) {
      Usuario usuario = usuarioService.buscarPorId(id);
      model.addAttribute("usuario", usuario);
      return "modificar_usuario";
    }

    // MODIFICAR USUARIO (POST)
    @PostMapping("/modificar")
    public String modificarUsuario(@ModelAttribute Usuario usuario) {
      usuarioService.modificarUsuario(usuario);
      return "redirect:/admin/lista";
    }

    // ELIMINAR USUARIO
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
      usuarioService.eliminarUsuario(id);
      return "redirect:/admin/lista";
    }

    // DESACTIVAR USUARIO
    @GetMapping("/desactivar/{id}")
    public String desactivarUsuario(@PathVariable Integer id) {
      usuarioService.desactivarUsuario(id);
      return "redirect:/admin/lista";
    }

    // ACTIVAR USUARIO
    @GetMapping("/activar/{id}")
    public String activarUsuario(@PathVariable Integer id) {
      usuarioService.activarUsuario(id);
      return "redirect:/admin/lista";
    }
  }


