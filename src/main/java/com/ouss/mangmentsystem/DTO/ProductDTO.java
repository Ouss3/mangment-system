package com.ouss.mangmentsystem.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Project Name: MangmentSystem
 * File Name: ProductDTO
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * ProductDTO is a part of the MangmentSystem project.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

   private Long id;
   private Long productId;
   private  Long categoryId;
   private Long supplierId;

    private String name;


     private String sku;

     @Positive(message = "Price must be positive")
     private BigDecimal price;

    @Min(value = 0, message = "Stock quantity cannot be less than 0")
     private Integer stockQuantity;

    private String description;

    private String imageUrl;

    private LocalDateTime expiredDate;

    private  LocalDateTime createdDate ;

    private LocalDateTime updatedDate ;

}
