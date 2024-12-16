package com.ouss.mangmentsystem.service.interfaces;

import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.Response;

/**
 * Project Name: MangmentSystem
 * File Name: CateforyService
 * Created by: DELL
 * Created on: 12/16/2024
 * Description:
 * <p>
 * CateforyService is a part of the MangmentSystem project.
 */

public interface CategoryService {

    Response createCategory(CategoryDTO categoryDTO);
    Response getAllCategories();
    Response getCategoryById(Long id);
    Response updateCategory(Long id, CategoryDTO categoryDTO);
    Response deleteCategory(Long id);


}
