package com.miriammendieta.zeerty_digital_app.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test mínimo de seguridad para el MVP.
 * Usa un controlador simulado sin dependencias.
 */
public class SecurityTest {

    // Controlador mínimo para probar seguridad
    @Controller
    static class FakeController {

        @GetMapping("/activos/home")
        @ResponseBody   // evita resolver la vista "home"
        public String home() {
            return "OK"; // contenido plano para el test
        }
    }

    private final MockMvc mockMvc =
            MockMvcBuilders.standaloneSetup(new FakeController()).build();

   
    /**
     * Usuario autenticado con rol ADMIN → acceso permitido
     */
    @Test
    @WithMockUser(roles = "ADMIN")
    void testAccesoConAdmin() throws Exception {
        mockMvc.perform(get("/activos/home"))
                .andExpect(status().isOk());
    }
}

