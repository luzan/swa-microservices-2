package com.example.transactionservice.controller;

import com.example.transactionservice.dto.TransactionDto;
import com.example.transactionservice.entities.Transaction;
import com.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("create-transaction")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction){
        TransactionDto transactionDto = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

}
