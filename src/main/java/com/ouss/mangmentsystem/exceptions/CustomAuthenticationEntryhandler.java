package com.ouss.mangmentsystem.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouss.mangmentsystem.DTO.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Project Name: MangmentSystem
 * File Name: CustomAccessDenialhandler
 * Created by: DELL
 * Created on: 12/14/2024
 * Description:
 * <p>
 * CustomAccessDenialhandler is a part of the MangmentSystem project.
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;



    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        Response errorResponse = Response.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(authException.getMessage())
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
