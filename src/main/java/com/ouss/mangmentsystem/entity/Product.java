package com.ouss.mangmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * Project Name: MangmentSystem
 * File Name: Product
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * Product is a part of the MangmentSystem project.
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

     @NotBlank(message = "SKU is required")
     @Column(unique = true)
     private String sku;

     @Positive(message = "Price must be positive")
     private BigDecimal price;

    @Min(value = 0, message = "Stock quantity cannot be less than 0")
     private Integer stockQuantity;

    private String description;

    private String imageUrl;

    private LocalDateTime expiredDate;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", expiredDate=" + expiredDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +

                '}';
    }
}
