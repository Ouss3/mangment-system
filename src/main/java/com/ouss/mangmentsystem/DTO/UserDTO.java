package com.ouss.mangmentsystem.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
   private String username;
   @JsonIgnore
  private String password;
   private String email;
   private String phone;
    private String address;
    private List<TransactionDTO> transaction;
  private UserRole role;
  private  LocalDateTime createdDate ;


}
