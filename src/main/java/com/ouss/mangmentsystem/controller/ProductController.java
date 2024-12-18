package com.ouss.mangmentsystem.controller;


import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.ProductDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.service.interfaces.CategoryService;
import com.ouss.mangmentsystem.service.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

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
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;



    @GetMapping("/all")
    public ResponseEntity<Response> getAllProducts() {


        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> saveProduct(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("name") String name,
            @RequestParam(value = "description" , required = false) String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("sku") String sku,
            @RequestParam("stockQuantity") Integer stockQuantity,
            @RequestParam("categoryId") Long categoryId

    ) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setSku(sku);
        productDTO.setStockQuantity(stockQuantity);
        productDTO.setCategoryId(categoryId);


        return ResponseEntity.ok(productService.saveProduct(productDTO, imageFile));
    }




    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProduct(
            @RequestParam(value = "imageFile" , required = false) MultipartFile imageFile,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description" , required = false) String description,
            @RequestParam(value = "price" ,required = false) BigDecimal price,
            @RequestParam(value = "sku" ,required = false) String sku,
            @RequestParam(value = "stockQuantity" ,required = false) Integer stockQuantity,
            @RequestParam(value = "categoryId",required = false ) Long categoryId,
            @RequestParam( value = "productId",required = true ) Long productId

    ) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setSku(sku);
        productDTO.setProductId(productId);
        productDTO.setStockQuantity(stockQuantity);
        productDTO.setCategoryId(categoryId);
       System.out.println(productDTO);

        return ResponseEntity.ok(productService.updateProduct(productDTO, imageFile));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }






}
