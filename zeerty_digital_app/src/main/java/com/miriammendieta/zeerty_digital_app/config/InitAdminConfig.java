package com.miriammendieta.zeerty_digital_app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.miriammendieta.zeerty_digital_app.model.Usuario;
import com.miriammendieta.zeerty_digital_app.model.Rol;
import com.miriammendieta.zeerty_digital_app.model.Estado;
import com.miriammendieta.zeerty_digital_app.repository.UsuarioRepository;

@Configuration
public class InitAdminConfig {

    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository usuarioRepository,
                                       PasswordEncoder passwordEncoder) {
        return args -> {

            // Crear admin solo si la tabla está vacía
            if (usuarioRepository.count() == 0) {

                String adminEmail = "admin@admin.com";

                Usuario admin = new Usuario(
                        "Administrador",
                        "Principal",
                        adminEmail,
                        passwordEncoder.encode("admin123"), // contraseña cifrada
                        "600000000",
                        Rol.ADMINISTRACION,
                        Estado.ACTIVO
                );

                usuarioRepository.save(admin);

                System.out.println("Administrador/a inicial creado correctamente");
            } else {
                System.out.println("Ya existen usuarios, no se crea administrador/a inicial");
            }
        };
    }
}
