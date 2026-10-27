package com.miriammendieta.zeerty_digital_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


// Controlador encargado de mostrar la vista de auditoría
@Controller
public class AuditoriaController {


    // Ruta GET que muestra la vista de auditoría
    @GetMapping("/registro-movimientos")
    public String verMovimientos(Model model) {

        //  nombre de la plantilla HTML ubicada en /templates/
        return "registro-movimientos";
    }
}
