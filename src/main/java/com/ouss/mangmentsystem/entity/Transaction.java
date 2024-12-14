package com.ouss.mangmentsystem.entity;

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


@Entity
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@Builder
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalProducts;

    private BigDecimal totalPrice;
   @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private String description;

    private LocalDateTime updatedDate;

    private final LocalDateTime createdDate = LocalDateTime.now();
   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", totalProducts=" + totalProducts +
                ", totalPrice=" + totalPrice +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", description='" + description + '\'' +
                ", updatedDate=" + updatedDate +
                ", createdDate=" + createdDate +
                '}';
    }
}
