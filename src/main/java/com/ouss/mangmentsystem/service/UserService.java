package com.ouss.mangmentsystem.service;

import com.ouss.mangmentsystem.DTO.LoginRequest;
import com.ouss.mangmentsystem.DTO.RegisterRequest;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.UserDTO;
import com.ouss.mangmentsystem.entity.User;

/**
 * Project Name: MangmentSystem
 * File Name: UserService
 * Created by: DELL
 * Created on: 12/15/2024
 * Description:
 * <p>
 * UserService is a part of the MangmentSystem project.
 */

public interface UserService {

    Response registerUser(RegisterRequest registerRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    User getCurrentLoggedInUser();
    Response updateUser(Long id, UserDTO userDTO);
    Response deleteUser(Long id);
    Response getUserTransactions(Long id);

}
