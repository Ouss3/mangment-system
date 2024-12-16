package com.ouss.mangmentsystem.service.implementation;

import com.ouss.mangmentsystem.DTO.LoginRequest;
import com.ouss.mangmentsystem.DTO.RegisterRequest;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.UserDTO;
import com.ouss.mangmentsystem.entity.User;
import com.ouss.mangmentsystem.enums.UserRole;
import com.ouss.mangmentsystem.exceptions.InvalidCredentialsException;
import com.ouss.mangmentsystem.exceptions.NotFoundException;
import com.ouss.mangmentsystem.repository.UserRepository;
import com.ouss.mangmentsystem.security.JwtUtils;
import com.ouss.mangmentsystem.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project Name: MangmentSystem
 * File Name: UserServiceImpl
 * Created by: DELL
 * Created on: 12/15/2024
 * Description:
 * <p>
 * UserServiceImpl is a part of the MangmentSystem project.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    @Override
    public Response registerUser(RegisterRequest registerRequest) {
        UserRole userRole = UserRole.MANAGER;
        if(registerRequest.getRole()!=null){
            userRole = registerRequest.getRole();
        }

        User userToSave = User.builder()
                .username(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhone())
                .role(userRole)
                .build();
         userRepository.save(userToSave);
        return Response.builder()
                .status(200)
                .message("User Registered Successfully")
                .build();

    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("password is incorrect");

        }
        String token = jwtUtils.generateToken(user.getEmail());

        return Response.builder()
                .status(200)
                .message("User Logged in Successfully")
                .role(user.getRole())
                .token(token)
                .expirationTime("10 days")
                .build();
    }

    @Override
    public Response getAllUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
        userDTOS.forEach(userDTO -> userDTO.setTransaction(null));
        return Response.builder()
                .status(200)
                .message("Users fetched successfully")
                .users(userDTOS)
                .build();

    }

    @Override
    public User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setTransaction(null);
        return user;
    }

    @Override
    public Response updateUser(Long id, UserDTO userDTO) {
       User  existingUser = userRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("User not found"));
       if(userDTO.getEmail()!=null)
           existingUser.setEmail(userDTO.getEmail());

       if (userDTO.getUsername()!=null)
           existingUser.setUsername(userDTO.getUsername());
       if (userDTO.getPhone()!=null)
           existingUser.setPhone(userDTO.getPhone());
       if (userDTO.getRole()!=null)
              existingUser.setRole(userDTO.getRole());
       if (userDTO.getPassword()!=null  && !userDTO.getPassword().isEmpty())
           existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

       userRepository.save(existingUser);
         return Response.builder()
                .status(200)
                .message("User updated successfully")
                .build();

    }

    @Override
    public Response deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.deleteById(id);
        return Response.builder()
                .status(200)
                .message("User deleted successfully")
                .build();
    }

    @Override
    public Response getUserTransactions(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.getTransaction().forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setSupplier(null);

        });
        return Response.builder()
                .status(200)
                .message("User transactions fetched successfully")
                .user(userDTO)
                .build();
    }
}
