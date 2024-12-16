package com.ouss.mangmentsystem.repository;

import com.ouss.mangmentsystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Project Name: MangmentSystem
 * File Name: SupplierRepository
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * SupplierRepository is a part of the MangmentSystem project.
 */
@EnableJpaRepositories
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
