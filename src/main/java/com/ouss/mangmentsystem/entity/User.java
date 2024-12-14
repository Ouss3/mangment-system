package com.ouss.mangmentsystem.entity;

import com.ouss.mangmentsystem.enums.TransactionType;
import com.ouss.mangmentsystem.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Project Name: MangmentSystem
 * File Name: UserDTO
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * UserDTO is a part of the MangmentSystem project.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter

@Builder@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Phone number is required")
    @Column(name = "phone_number")
    private String phone;
    @NotBlank(message = "Address is required")
    private String address;
   @OneToMany(mappedBy = "user")
    private List<Transaction> transaction;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "created_date")
    private final LocalDateTime createdDate = LocalDateTime.now();



    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", createdDate=" + createdDate +
                '}';
    }
}
