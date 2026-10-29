package com.miriammendieta.zeerty_digital_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.miriammendieta.zeerty_digital_app.security.InactivoFailureHandler;

/*
 * Configuración de seguridad de Spring Security.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final CustomUserDetailsService customUserDetailsService;
        private final CustomSuccessHandler customSuccessHandler;

        public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                        CustomSuccessHandler customSuccessHandler) {
                this.customUserDetailsService = customUserDetailsService;
                this.customSuccessHandler = customSuccessHandler;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .csrf(csrf -> csrf.disable())
                                // Servicio que carga el usuario desde la BD
                                .userDetailsService(customUserDetailsService)
                                // Rutas públicas y privadas
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/index", "/entrar", "/login", "/procesar-login",
                                                                "/css/**", "/js/**", "/img/**")
                                                .permitAll()

                                                // ADMIN
                                                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMINISTRACION")
                                                .requestMatchers("/activos/**").hasAuthority("ROLE_ADMINISTRACION")

                                                // AUDITOR + ADMIN
                                                .requestMatchers("/registro-movimientos/**")
                                                .hasAnyAuthority("ROLE_ADMINISTRACION", "ROLE_AUDITORIA")

                                                // HOME para todos los roles válidos
                                                .requestMatchers("/home")
                                                .hasAnyAuthority("ROLE_USUARIO_APP", "ROLE_ADMINISTRACION",
                                                                "ROLE_AUDITORIA", "ROLE_INVITADO")

                                                .anyRequest().authenticated())

                                // Configuración del login
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/procesar-login")
                                                .successHandler(customSuccessHandler)
                                                .failureHandler(new InactivoFailureHandler())
                                                .permitAll())

                                // Configuración de logout
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true)
                                                .logoutSuccessUrl("/login"));

                http.exceptionHandling(ex -> ex
                                .authenticationEntryPoint((request, response, authException) -> {

                                        Throwable causa = authException.getCause();

                                        if (causa instanceof DisabledException) {
                                                response.sendRedirect("/login?inactivo");
                                                return;
                                        }
                                        // Usuario o contraseña incorrectos
                                        response.sendRedirect("/login?error");
                                }));

                return http.build();
        }
}