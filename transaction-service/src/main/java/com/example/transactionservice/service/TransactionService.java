package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransactionDto;
import com.example.transactionservice.entities.Transaction;

public interface TransactionService {

    public TransactionDto saveTransaction(Transaction transaction);
}
