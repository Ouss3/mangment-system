package com.ouss.mangmentsystem.service.implementation;

import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.ProductDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.entity.Category;
import com.ouss.mangmentsystem.entity.Product;
import com.ouss.mangmentsystem.repository.CategoryRepository;
import com.ouss.mangmentsystem.repository.ProductRepository;
import com.ouss.mangmentsystem.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.UUID;

/**
 * Project Name: MangmentSystem
 * File Name: ProductServiceImpl
 * Created by: DELL
 * Created on: 12/16/2024
 * Description:
 * <p>
 * ProductServiceImpl is a part of the MangmentSystem project.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    private final String IMAGE_PATH = System.getProperty("user.dir")+"/product-image/";
    @Override
    public Response saveProduct(ProductDTO productDTO, MultipartFile imageFile) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new IllegalArgumentException("Category not found"));

        Product product = Product.builder()
                .name(productDTO.getName())
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .stockQuantity(productDTO.getStockQuantity())
                .price(productDTO.getPrice())
                .category(category)
                .build();
        if (imageFile != null){
            String imagePath = saveImage(imageFile);
            product.setImageUrl(imagePath);
        }
        productRepository.save(product);
        return Response.builder()
                .status(200)
                .message("Product added successfully")
                .build();

    }

    @Override
    public Response getAllProduct() {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ProductDTO> productDTOS = modelMapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Category fetched successfully")
                .products(productDTOS)
                .build();
    }

    @Override
    public Response getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return Response.builder()
                .status(200)
                .message("Product fetched successfully")
                .product(productDTO)
                .build();
    }

    @Override
    public Response updateProduct(ProductDTO productDTO, MultipartFile imageFile) {
       Product product = productRepository.findById(productDTO.getProductId())
               .orElseThrow(()-> new IllegalArgumentException("Product not found"));

       if (imageFile != null){
           String imagePath = saveImage(imageFile);
           product.setImageUrl(imagePath);
       }
       if (productDTO.getCategoryId()!=null && productDTO.getCategoryId()>0){
         Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new IllegalArgumentException("Category not found"));
         product.setCategory(category);
       }

       if (productDTO.getName()!=null && !productDTO.getName().isBlank()){
           product.setName(productDTO.getName());
       }
         if (productDTO.getDescription()!=null && !productDTO.getDescription().isBlank()){
              product.setDescription(productDTO.getDescription());
         }
            if (productDTO.getPrice()!=null && productDTO.getPrice().compareTo(BigDecimal.ZERO) >=0){
                product.setPrice(productDTO.getPrice());
            }
            if (productDTO.getStockQuantity()!=null && productDTO.getStockQuantity()>=0){
                product.setStockQuantity(productDTO.getStockQuantity());
            }
            if (productDTO.getSku()!=null && !productDTO.getSku().isBlank()){
                product.setSku(productDTO.getSku());
            }
            product.setUpdatedDate(LocalDateTime.now());
            productRepository.save(product);

            return Response.builder()
                    .status(200)
                    .message("Product updated successfully")
                    .build();

    }

    @Override
    public Response deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));
        productRepository.deleteById(id);
        return Response.builder()
                .status(200)
                .message("Product deleted successfully")
                .build();
    }


    private String saveImage(MultipartFile imageFile){
       if (!imageFile.getContentType().startsWith("image/")){
           throw new IllegalArgumentException("File must be an image");
       }

        File dir = new File(IMAGE_PATH);
        if (!dir.exists()){
            dir.mkdir();
            log.info("Directory created");
        }

        String fileName = UUID.randomUUID()+"_"+imageFile.getOriginalFilename();
        String imagePath = IMAGE_PATH+fileName;
        try {
             File descFile = new File(imagePath);
            imageFile.transferTo(descFile);

        }catch (Exception e){
            log.error("Error occurred while saving image", e);
            throw new IllegalArgumentException("Error occurred while saving image");
        }
        return imagePath;


    }
}
