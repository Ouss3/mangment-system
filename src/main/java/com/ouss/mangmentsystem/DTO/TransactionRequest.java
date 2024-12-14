package com.ouss.mangmentsystem.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project Name: MangmentSystem
 * File Name: TransactionRequest
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * TransactionRequest is a part of the MangmentSystem project.
 */

@Data
@AllArgsConstructor@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequest {

    @Positive(message = "ProductDTO id must be positive")
    private Long productId;
    @Positive(message = "Quantity must be positive")
    private  Integer quantity;
    private Long supplierId;
    private String description;
}
