package com.swa.bankAccount.controller;

import com.swa.bankAccount.dto.BankAccountDto;
import com.swa.bankAccount.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;


    @PostMapping
    public ResponseEntity<?> saveBankAccount(@RequestBody BankAccountDto bankAccountDto) {
        ResponseEntity<BankAccountDto> response = new ResponseEntity<>(bankAccountService.save(bankAccountDto), HttpStatus.OK);
        return response;
    }

    @PutMapping("/verify-purchase")
    public ResponseEntity<?> checkBankAccount(@RequestBody BankAccountDto bankAccountDto) {
        ResponseEntity<?> response = new ResponseEntity<>(bankAccountService.checkBankAccount(bankAccountDto), HttpStatus.OK);
        return response;
    }



}
