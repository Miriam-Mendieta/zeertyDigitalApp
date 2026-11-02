package com.miriammendieta.zeerty_digital_app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpSession;

import com.miriammendieta.zeerty_digital_app.service.ActivoDigitalService;
import com.miriammendieta.zeerty_digital_app.service.LogMovimientoService;
import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.model.Rol;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// Carga únicamente ActivoDigitalController y su capa MVC
@WebMvcTest(ActivoDigitalController.class)
public class ActivoControllerTest {

    // MockMvc permite simular peticiones HTTP sin levantar el servidor
    @Autowired
    private MockMvc mockMvc;

    // Mock de los servicios que usa el controlador
    // (si no se mockean, WebMvcTest no puede instanciar el controlador)
    @MockitoBean
    private ActivoDigitalService service;

    @MockitoBean
    private LogMovimientoService logMovimientoService;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testListarActivosView() throws Exception {// Simula un usuario autenticado para pasar la seguridad

        // Crear usuario simulado para la sesión (Thymeleaf lo necesita)
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setRol(Rol.ADMINISTRACION); // Rol es un ENUM: se asigna directamente

        // Simular sesión HTTP con el usuario logueado
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usuario", usuario);

        // Ejecutar GET /activos/home con la sesión simulada
        mockMvc.perform(get("/activos/home").session(session))
                .andExpect(status().isOk()) // El controlador debe devolver 200 OK
                .andExpect(view().name("activos/home")); // Se debe renderizar la vista Thymeleaf correcta
    }
}
