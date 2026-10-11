package com.miriammendieta.zeerty_digital_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioHomeController {

    @GetMapping("/home")
    public String homeUsuario() {
        return "home"; // archivo en templates
    }
}
