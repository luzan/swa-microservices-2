package com.example.accountservice.controller;

import com.example.accountservice.entity.Account;
import com.example.accountservice.service.AccountService;
import com.example.commonmodule.dtos.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.saveAccount(account), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccountByUserId(id), HttpStatus.OK);
    }
}
