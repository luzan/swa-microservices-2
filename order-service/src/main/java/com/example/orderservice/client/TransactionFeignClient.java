package com.example.orderservice.client;

import com.example.orderservice.dto.TransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TRANSACTION-SERVICE", url = "${app.url.transaction-service}")
public interface TransactionFeignClient {

    @PostMapping("/transaction/create-transaction")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto);
}
