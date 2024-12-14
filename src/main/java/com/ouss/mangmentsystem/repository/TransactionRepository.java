package com.ouss.mangmentsystem.repository;

import com.ouss.mangmentsystem.entity.Transaction;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.awt.print.Pageable;
import java.util.List;

/**
 * Project Name: MangmentSystem
 * File Name: TransactionRepository
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * TransactionRepository is a part of the MangmentSystem project.
 */

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t FROM TransactionDTO t " +
            "WHERE YEAR(t.createdDate)= : year AND MONTH(t.createdDate)= : month")
    List<Transaction> findAllByMonthAndYear(@Param("year") int year, @Param("month") int month);


    @Query("SELECT t FROM TransactionDTO t " +
            "LEFT JOIN t.product p " +
             "where (:searchText IS NULL OR "+
             "LOWER(t.description) LIKE LOWER(CONCAT('%',:searchText,'%')) OR "+
                "LOWER(p.name) LIKE LOWER(CONCAT('%',:searchText,'%')) OR "+
             "LOWER(t.transactionStatus) LIKE LOWER(CONCAT('%',:searchText,'%')) OR "+
            "LOWER(p.sku) LIKE LOWER(CONCAT('%',:searchText,'%')) ) ")
    Page<Transaction> searchTransactions(@Param("searchText") String searchText, Pageable pageable);
}
