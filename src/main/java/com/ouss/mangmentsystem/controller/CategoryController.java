package com.ouss.mangmentsystem.controller;


import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.UserDTO;
import com.ouss.mangmentsystem.entity.User;
import com.ouss.mangmentsystem.service.interfaces.CategoryService;
import com.ouss.mangmentsystem.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Project Name: MangmentSystem
 * File Name: AuthController
 * Created by: DELL
 * Created on: 12/15/2024
 * Description:
 * <p>
 * AuthController is a part of the MangmentSystem project.
 */
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;



    @GetMapping("/all")
    public ResponseEntity<Response> getAllCategories() {


        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {


        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }




    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }






}
