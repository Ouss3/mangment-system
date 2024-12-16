package com.ouss.mangmentsystem.service.implementation;

import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.UserDTO;
import com.ouss.mangmentsystem.entity.Category;
import com.ouss.mangmentsystem.entity.User;
import com.ouss.mangmentsystem.exceptions.NotFoundException;
import com.ouss.mangmentsystem.repository.CategoryRepository;
import com.ouss.mangmentsystem.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project Name: MangmentSystem
 * File Name: CategoryServiceImpl
 * Created by: DELL
 * Created on: 12/16/2024
 * Description:
 * <p>
 * CategoryServiceImpl is a part of the MangmentSystem project.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createCategory(CategoryDTO categoryDTO) {
        Category categoryToSave = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(categoryToSave);
         return Response.builder()
                .status(200)
                .message("category created successfully")
                .build();
    }

    @Override
    public Response getAllCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));



        List<CategoryDTO> categoryDTOS = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Users fetched successfully")
                .categories(categoryDTOS)
                .build();
    }

    @Override
    public Response getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Category not found"));

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return Response.builder()
                .status(200)
                .message("Category fetched successfully")
                .category(categoryDTO)
                .build();
    }

    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Category not found"));

        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return Response.builder()
                .status(200)
                .message("Category updated successfully")
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        categoryRepository.deleteById(id);
        return Response.builder()
                .status(200)
                .message("Category deleted successfully")
                .build();
    }
}
