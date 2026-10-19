package com.miriammendieta.zeerty_digital_app.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public class InactivoFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException {

        // Spring Security envuelve DisabledException dentro de InternalAuthenticationServiceException
        Throwable causa = exception.getCause();

        if (causa instanceof DisabledException) {
            response.sendRedirect("/login?inactivo");
            return;
        }

        response.sendRedirect("/login?error");
    }
}

