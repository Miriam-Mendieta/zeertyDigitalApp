package com.miriammendieta.zeerty_digital_app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.miriammendieta.zeerty_digital_app.service.ActivoDigitalService;



@Controller
@RequestMapping("/activos")
public class ActivoDigitalController {

    private final ActivoDigitalService service = new ActivoDigitalService();

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("listaActivos", service.listar());
        return "home"; // home.html
    }
}
