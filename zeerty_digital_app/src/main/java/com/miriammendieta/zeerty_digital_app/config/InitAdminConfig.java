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

            try {
                // Intentar contar usuarios
                long count = usuarioRepository.count();

                if (count == 0) {

                    String adminEmail = "admin@admin.com";

                    Usuario admin = new Usuario(
                            "Administrador",
                            "Principal",
                            adminEmail,
                            passwordEncoder.encode("admin123"),
                            "600000000",
                            Rol.ADMINISTRACION,
                            Estado.ACTIVO);

                    usuarioRepository.save(admin);

                    System.out.println("Administrador/a inicial creado correctamente");
                } else {
                    System.out.println("Ya existen usuarios, no se crea administrador/a inicial");
                }

            } catch (Exception e) {
                // Si la tabla no existe o hay un error de SQL, NO romper la app
                System.out.println(
                        "No se pudo inicializar el administrador porque la tabla 'usuarios' no existe todavía.");
                System.out.println("Spring Boot continuará sin crear el admin.");
            }
        };
    }
}
