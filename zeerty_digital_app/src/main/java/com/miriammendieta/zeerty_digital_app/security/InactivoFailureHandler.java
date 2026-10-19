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

        if (exception instanceof DisabledException) {
            response.sendRedirect("/login?inactivo");
        } else {
            response.sendRedirect("/login?error");
        }
    }
}
