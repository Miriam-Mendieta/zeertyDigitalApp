package com.miriammendieta.zeerty_digital_app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.miriammendieta.zeerty_digital_app.model.Usuario;// entidad
import com.miriammendieta.zeerty_digital_app.model.Rol; // enum Rol
import com.miriammendieta.zeerty_digital_app.model.Estado;// enum Estado
import com.miriammendieta.zeerty_digital_app.repository.UsuarioRepository;


@Configuration
public class InitAdminConfig {
    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository usuarioRepository,
                                       PasswordEncoder passwordEncoder) {
        return args -> {

            // 1️⃣ Correo del administrador
            String adminEmail = "miriam.mendieta11@gmail.com";

            // se comprueba si ya existe en la BD, si no existe se crea
            if (usuarioRepository.findByEmail(adminEmail).isEmpty()) {

                // se crea el administrador usando el constructor
                Usuario admin = new Usuario(
                        "Miriam",                       // nombre
                        "Mendieta",                 // apellidos
                        adminEmail,                           // email
                        passwordEncoder.encode("admin123"),  // passwordHash (cifrada)
                        "600000000",               // teléfono
                        Rol.ADMINISTRACION,                 // enum Rol
                        Estado.ACTIVO                      // enum Estado
                );

                // se guarda en la base de datos
                usuarioRepository.save(admin);

                System.out.println("Administrador creado correctamente");
            } else {
                System.out.println("El administrador ya existe");
            }
        };
    }
}


