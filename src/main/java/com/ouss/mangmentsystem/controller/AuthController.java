package com.ouss.mangmentsystem.controller;

import com.ouss.mangmentsystem.DTO.LoginRequest;
import com.ouss.mangmentsystem.DTO.RegisterRequest;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project Name: MangmentSystem
 * File Name: AuthController
 * Created by: DELL
 * Created on: 12/15/2024
 * Description:
 * <p>
 * AuthController is a part of the MangmentSystem project.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;



    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }


    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }
}
