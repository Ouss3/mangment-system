package com.ouss.mangmentsystem.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ouss.mangmentsystem.enums.TransactionStatus;
import com.ouss.mangmentsystem.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Project Name: MangmentSystem
 * File Name: TransactionDTO
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * TransactionDTO is a part of the MangmentSystem project.
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

   private Long id;

    private Integer totalProducts;

    private BigDecimal totalPrice;
   private TransactionType transactionType;
 private TransactionStatus transactionStatus;

    private String description;

    private LocalDateTime updatedDate;

    private LocalDateTime createdDate ;
  private UserDTO user;

    private ProductDTO product;

  private SupplierDTO supplier;

}
