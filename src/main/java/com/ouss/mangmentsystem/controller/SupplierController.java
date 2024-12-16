package com.ouss.mangmentsystem.controller;


import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.SupplierDTO;
import com.ouss.mangmentsystem.service.interfaces.CategoryService;
import com.ouss.mangmentsystem.service.interfaces.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;



    @GetMapping("/all")
    public ResponseEntity<Response> getAllCategories() {


        return ResponseEntity.ok(supplierService.getAllSupplier());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createCategory(@RequestBody @Valid SupplierDTO supplierDTO) {


        return ResponseEntity.ok(supplierService.addSupplier(supplierDTO));
    }




    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateCategory(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, supplierDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }






}
