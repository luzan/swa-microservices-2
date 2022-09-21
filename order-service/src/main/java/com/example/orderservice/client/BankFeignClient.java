package com.example.orderservice.client;

import com.example.orderservice.dto.BankAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BANK-SERVICE", url = "${app.url.bank-service}")
public interface BankFeignClient {

    @PutMapping("/bankaccounts/verify-purchase")
    public ResponseEntity<Boolean> verifyPurchase(@RequestBody BankAccountDto bankAccountDto);

    @PostMapping("/bankaccounts")
    public ResponseEntity<BankAccountDto> saveBankAccount(@RequestBody BankAccountDto bankAccountDto);
}
