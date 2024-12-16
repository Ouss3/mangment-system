package com.ouss.mangmentsystem.repository;

import com.ouss.mangmentsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Project Name: MangmentSystem
 * File Name: CategoryRepository
 * Created by: DELL
 * Created on: 12/13/2024
 * Description:
 * <p>
 * CategoryRepository is a part of the MangmentSystem project.
 */
@EnableJpaRepositories
public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
