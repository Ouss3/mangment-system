package com.ouss.mangmentsystem.service.interfaces;

import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.TransactionRequest;
import com.ouss.mangmentsystem.enums.TransactionStatus;

/**
 * Project Name: MangmentSystem
 * File Name: CateforyService
 * Created by: DELL
 * Created on: 12/16/2024
 * Description:
 * <p>
 * CateforyService is a part of the MangmentSystem project.
 */

public interface TransactionService {

    Response restockInventory(TransactionRequest transactionRequest);
    Response sell(TransactionRequest transactionRequest);
    Response returnToSupplier(TransactionRequest transactionRequest);
    Response getAllTransactions(int page, int size ,String serachText);
    Response getTransactionById(Long id);
    Response getAllTransactionsByMonthAndYear(int month, int year);
    Response getTransactionStatus(Long id , TransactionStatus transactionStatus);


}
