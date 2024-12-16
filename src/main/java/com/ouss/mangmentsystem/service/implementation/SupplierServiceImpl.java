package com.ouss.mangmentsystem.service.implementation;

import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.SupplierDTO;
import com.ouss.mangmentsystem.entity.Category;
import com.ouss.mangmentsystem.entity.Supplier;
import com.ouss.mangmentsystem.exceptions.NotFoundException;
import com.ouss.mangmentsystem.repository.CategoryRepository;
import com.ouss.mangmentsystem.repository.SupplierRepository;
import com.ouss.mangmentsystem.service.interfaces.CategoryService;
import com.ouss.mangmentsystem.service.interfaces.SupplierService;
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
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response addSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        supplierRepository.save(supplier);
         return Response.builder()
                .status(200)
                .message("Supplier added successfully")
                .build();
    }

    @Override
    public Response getAllSupplier() {
        List<Supplier> suppliers = supplierRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));



        List<SupplierDTO> supplierDTOS = modelMapper.map(suppliers, new TypeToken<List<CategoryDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Supplier fetched successfully")
                .suppliers(supplierDTOS)
                .build();
    }

    @Override
    public Response getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Supplier not found"));

        SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);

        return Response.builder()
                .status(200)
                .message("Supplier fetched successfully")
                .supplier(supplierDTO)
                .build();
    }

    @Override
    public Response updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Supplier not found"));

        if (supplierDTO.getName() != null) {
            supplier.setName(supplierDTO.getName());
        }
        if (supplierDTO.getAddress()!=null){
            supplier.setAddress(supplierDTO.getAddress());
        }
        supplierRepository.save(supplier);
        return Response.builder()
                .status(200)
                .message("Supplier updated successfully")
                .build();
    }

    @Override
    public Response deleteSupplier(Long id) {
        supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier not found"));

        supplierRepository.deleteById(id);
        return Response.builder()
                .status(200)
                .message("Supplier deleted successfully")
                .build();
    }
}
