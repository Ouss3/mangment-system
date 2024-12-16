package com.ouss.mangmentsystem.controller;


import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.UserDTO;
import com.ouss.mangmentsystem.entity.User;
import com.ouss.mangmentsystem.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + authentication.getAuthorities());

        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Response> getUserTransactions(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserTransactions(id));
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentLoggedInUser() {
        return ResponseEntity.ok(userService.getCurrentLoggedInUser()   );
    }


}
