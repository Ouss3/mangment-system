package com.ouss.mangmentsystem.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ouss.mangmentsystem.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Project Name: MangmentSystem
 * File Name: Response
 * Created by: DELL
 * Created on: 12/14/2024
 * Description:
 * <p>
 * Response is a part of the MangmentSystem project.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    // generic
    private int status;
    private String message;

    // for login
    private String token;
    private UserRole role;
    private String expirationTime;

    //for pagination
    private Integer totalPages;
    private Long totalElements;

    //data output optianal
    private UserDTO user;
    private List<UserDTO> users;

    private ProductDTO product;
    private List<ProductDTO> products;

    private CategoryDTO category;
    private List<CategoryDTO> categories;

    private SupplierDTO supplier;
    private List<SupplierDTO> suppliers;

    private TransactionDTO transaction;
    private List<TransactionDTO> transactions;

    private final LocalDateTime timestamp = LocalDateTime.now();






}
