package com.ouss.mangmentsystem.service.interfaces;


import com.ouss.mangmentsystem.DTO.ProductDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.SupplierDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Project Name: MangmentSystem
 * File Name: CateforyService
 * Created by: DELL
 * Created on: 12/16/2024
 * Description:
 * <p>
 * CateforyService is a part of the MangmentSystem project.
 */

public interface ProductService {

    Response saveProduct(ProductDTO productDTO, MultipartFile imageFile);
    Response getAllProduct();
    Response getProductById(Long id);
    Response updateProduct(ProductDTO productDTO, MultipartFile imageFile);
    Response deleteProduct(Long id);


}
