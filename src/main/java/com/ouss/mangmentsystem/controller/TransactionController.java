package com.ouss.mangmentsystem.controller;


import com.ouss.mangmentsystem.DTO.CategoryDTO;
import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.TransactionDTO;
import com.ouss.mangmentsystem.DTO.TransactionRequest;
import com.ouss.mangmentsystem.enums.TransactionStatus;
import com.ouss.mangmentsystem.service.interfaces.CategoryService;
import com.ouss.mangmentsystem.service.interfaces.TransactionService;
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
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;



    @GetMapping("/all")
    public ResponseEntity<Response> getAllTransactions(
            @RequestParam( defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String searchText
    ) {


        return ResponseEntity.ok(transactionService.getAllTransactions(page, size, searchText));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PostMapping("/purchase")
    public ResponseEntity<Response> restockInventory(@RequestBody @Valid TransactionRequest transactionRequest) {


        return ResponseEntity.ok(transactionService.restockInventory(transactionRequest));
    }

    @PostMapping("/sell")
    public ResponseEntity<Response> sell(@RequestBody @Valid TransactionRequest transactionRequest) {


        return ResponseEntity.ok(transactionService.sell(transactionRequest));
    }
    @PostMapping("/return")
    public ResponseEntity<Response> returnToSupplier(@RequestBody @Valid TransactionRequest transactionRequest) {


        return ResponseEntity.ok(transactionService.returnToSupplier(transactionRequest));
    }



    @GetMapping("/byMonthAndYear")
    public ResponseEntity<Response> getAllTransactionByMAndY(
            @RequestParam int month,
            @RequestParam int year

    ) {
        System.out.println("Month: " + month + " Year: " + year);

        return ResponseEntity.ok(transactionService.getAllTransactionsByMonthAndYear(month, year));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateTransactionStatus(
            @PathVariable Long id,
            @RequestBody @Valid TransactionStatus transactionStatus) {
        return ResponseEntity.ok(transactionService.getTransactionStatus(id, transactionStatus));
    }








}
