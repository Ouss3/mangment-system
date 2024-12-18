package com.ouss.mangmentsystem.service.implementation;

import com.ouss.mangmentsystem.DTO.Response;
import com.ouss.mangmentsystem.DTO.TransactionDTO;
import com.ouss.mangmentsystem.DTO.TransactionRequest;
import com.ouss.mangmentsystem.entity.Product;
import com.ouss.mangmentsystem.entity.Supplier;
import com.ouss.mangmentsystem.entity.Transaction;
import com.ouss.mangmentsystem.entity.User;
import com.ouss.mangmentsystem.enums.TransactionStatus;
import com.ouss.mangmentsystem.enums.TransactionType;
import com.ouss.mangmentsystem.exceptions.NameValueRequiredException;
import com.ouss.mangmentsystem.exceptions.NotFoundException;
import com.ouss.mangmentsystem.repository.ProductRepository;
import com.ouss.mangmentsystem.repository.SupplierRepository;
import com.ouss.mangmentsystem.repository.TransactionRepository;
import com.ouss.mangmentsystem.service.interfaces.TransactionService;
import com.ouss.mangmentsystem.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Project Name: MangmentSystem
 * File Name: TransactionServiceImpl
 * Created by: DELL
 * Created on: 12/18/2024
 * Description:
 * <p>
 * TransactionServiceImpl is a part of the MangmentSystem project.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    @Override
    public Response restockInventory(TransactionRequest transactionRequest) {
       Long productId = transactionRequest.getProductId();
       Long supplierId = transactionRequest.getSupplierId();
       Integer quantity = transactionRequest.getQuantity();

       if (supplierId ==null)throw new NameValueRequiredException("Supplier Id is required");

       Product product = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));

       Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(()-> new IllegalArgumentException("Supplier not found"));

        User user = userService.getCurrentLoggedInUser();

        product.setStockQuantity(product.getStockQuantity()+quantity);
        productRepository.save(product);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.PURCHASE)
                .transactionStatus(TransactionStatus.COMPLETED)
                .product(product)
                .user(user)
                .supplier(supplier)
                .totalProducts(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .description(transactionRequest.getDescription())
                .build();
        transactionRepository.save(transaction);
        return Response.builder()
                .status(200)
                .message("transaction completed successfully")
                .build();
    }

    @Override
    public Response sell(TransactionRequest transactionRequest) {
        Long productId = transactionRequest.getProductId();

        Integer quantity = transactionRequest.getQuantity();


        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));



        User user = userService.getCurrentLoggedInUser();

        product.setStockQuantity(product.getStockQuantity()-quantity);
        productRepository.save(product);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.SALE)
                .transactionStatus(TransactionStatus.COMPLETED)
                .product(product)
                .user(user)
                .totalProducts(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .description(transactionRequest.getDescription())
                .build();
        transactionRepository.save(transaction);
        return Response.builder()
                .status(200)
                .message("transaction sold successfully")
                .build();
    }

    @Override
    public Response returnToSupplier(TransactionRequest transactionRequest) {
        Long productId = transactionRequest.getProductId();
        Long supplierId = transactionRequest.getSupplierId();
        Integer quantity = transactionRequest.getQuantity();

        if (supplierId ==null)throw new NameValueRequiredException("Supplier Id is required");

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(()-> new IllegalArgumentException("Supplier not found"));

        User user = userService.getCurrentLoggedInUser();

        product.setStockQuantity(product.getStockQuantity()-quantity);
        productRepository.save(product);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.RETURN_TO_SUPPLIER)
                .transactionStatus(TransactionStatus.COMPLETED)
                .product(product)
                .user(user)
                .supplier(supplier)
                .totalProducts(quantity)
                .totalPrice(BigDecimal.ZERO)
                .description(transactionRequest.getDescription())
                .build();
        transactionRepository.save(transaction);
        return Response.builder()
                .status(200)
                .message("transaction Return successfully")
                .build();
    }

    @Override
    public Response getAllTransactions(int page, int size, String serachText) {
        Pageable pageable = PageRequest.of(page, size , Sort.by(Sort.Direction.DESC, "id"));
        Page<Transaction> transactions = transactionRepository.searchTransactions(serachText, pageable);
        List<TransactionDTO> transactionDTOS = modelMapper.map(
                transactions.getContent(),
                new TypeToken<List<TransactionDTO>>(){}.getType());
       transactionDTOS.forEach(transactionDTO -> {
           transactionDTO.setUser(null);
           transactionDTO.setProduct(null);
           transactionDTO.setSupplier(null);
       });
        return Response.builder()
                .status(200)
                .message("Transactions fetched successfully")
                .transactions(transactionDTOS)
                .build();

    }

    @Override
    public Response getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Transaction not found"));
        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
         transactionDTO.getUser().setTransaction(null);
         return Response.builder()
                .status(200)
                .message("Transaction fetched successfully")
                .transaction(transactionDTO)
                .build();
    }

    @Override
    public Response getAllTransactionsByMonthAndYear(int month, int year) {
        System.out.println("Month: " + month + " Year: " + year +"From Transaction Service");
        List<Transaction> transactions = transactionRepository.findAllByMonthAndYear(month, year);



        List<TransactionDTO> transactionDTOS = modelMapper.map(
                transactions, new TypeToken<List<TransactionDTO>>(){}.getType());

        transactionDTOS.forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setProduct(null);
            transactionDTO.setSupplier(null);
        });
        return Response.builder()
                .status(200)
                .message("Transactions fetched successfully")
                .transactions(transactionDTOS)
                .build();
    }

    @Override
    public Response getTransactionStatus(Long id, TransactionStatus transactionStatus) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Transaction not found"));
        transaction.setTransactionStatus(transactionStatus);
        transaction.setUpdatedDate(LocalDateTime.now());


      transactionRepository.save(transaction);

        return Response.builder()
                .status(200)
                .message("Transaction fetched successfully")
                .build();
    }
}
