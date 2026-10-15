package com.miriammendieta.zeerty_digital_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String inicio() {
        return "index"; // index.html en /templates
    }

    @GetMapping("/entrar")
    public String entrar() {
        return "entrar"; // entrar.html
    }
}
