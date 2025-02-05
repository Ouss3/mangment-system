package com.ouss.mangmentsystem.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouss.mangmentsystem.DTO.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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

public class CustomAccessDenialhandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    public CustomAccessDenialhandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        com.ouss.mangmentsystem.DTO.Response errorResponse = com.ouss.mangmentsystem.DTO.Response.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(accessDeniedException.getMessage())
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));

    }
}
