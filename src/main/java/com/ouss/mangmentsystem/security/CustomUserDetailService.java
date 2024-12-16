package com.ouss.mangmentsystem.security;

import com.ouss.mangmentsystem.entity.User;
import com.ouss.mangmentsystem.exceptions.NotFoundException;
import com.ouss.mangmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Project Name: MangmentSystem
 * File Name: CustomUserDetailService
 * Created by: DELL
 * Created on: 12/15/2024
 * Description:
 * <p>
 * CustomUserDetailService is a part of the MangmentSystem project.
 */
@Service

public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User  Email not found"));
        return AuthUser.builder().user(user).build();
    }
}
