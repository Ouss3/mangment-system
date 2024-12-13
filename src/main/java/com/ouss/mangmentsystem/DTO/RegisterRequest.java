package com.ouss.mangmentsystem.DTO;

import com.ouss.mangmentsystem.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project Name: MangmentSystem
 * File Name: RegisterRequest
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * RegisterRequest is a part of the MangmentSystem project.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Phone number is required")
    private String phone;
    private UserRole role;

}
