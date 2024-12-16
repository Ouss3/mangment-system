package com.ouss.mangmentsystem.service.interfaces;


import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.SupplierDTO;

/**
 * Project Name: MangmentSystem
 * File Name: CateforyService
 * Created by: DELL
 * Created on: 12/16/2024
 * Description:
 * <p>
 * CateforyService is a part of the MangmentSystem project.
 */

public interface SupplierService {

    Response addSupplier(SupplierDTO supplierDTO);
    Response getAllSupplier();
    Response getSupplierById(Long id);
    Response updateSupplier(Long id, SupplierDTO supplierDTO);
    Response deleteSupplier(Long id);


}
