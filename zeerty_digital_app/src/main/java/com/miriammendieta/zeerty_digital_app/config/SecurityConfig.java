package com.miriammendieta.zeerty_digital_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .userDetailsService(customUserDetailsService) // ← funciona perfecto
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/entrar", "/login", "/procesar-login",
                                "/css/**", "/js/**", "/img/**")
                        .permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMINISTRACION")
                        .requestMatchers("/home").hasAnyRole("USUARIO_APP", "ADMINISTRACION", "AUDITORIA", "INVITADO")
                        .anyRequest().authenticated()
                    )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/procesar-login")
                        .successHandler(customSuccessHandler)
                        .failureHandler(new InactivoFailureHandler())
                        .permitAll()
                    )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/login"));

        return http.build();
    }
}
