package com.ouss.mangmentsystem.repository;

import com.ouss.mangmentsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Project Name: MangmentSystem
 * File Name: ProductRepository
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * ProductRepository is a part of the MangmentSystem project.
 */
@EnableJpaRepositories
public interface ProductRepository  extends JpaRepository<Product, Long> {
}
