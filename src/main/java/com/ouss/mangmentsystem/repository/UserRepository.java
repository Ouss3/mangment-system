package com.ouss.mangmentsystem.repository;

import com.ouss.mangmentsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Project Name: MangmentSystem
 * File Name: UserRepository
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * UserRepository is a part of the MangmentSystem project.
 */

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
