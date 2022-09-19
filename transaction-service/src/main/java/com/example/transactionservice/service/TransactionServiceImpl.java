package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransactionDto;
import com.example.transactionservice.entities.Transaction;
import com.example.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public TransactionDto saveTransaction(Transaction transaction) {
        Transaction saveTransaction  = transactionRepository.save(transaction);
        TransactionDto transactionDto = TransactionDto.builder()
                .orderId(saveTransaction.getOrderId())
                .transactionCode(saveTransaction.getTransactionCode())
                .paymentMethod(saveTransaction.getPaymentMethod())
                .total(saveTransaction.getTotal())
                .build();
        return transactionDto;
    }
}
