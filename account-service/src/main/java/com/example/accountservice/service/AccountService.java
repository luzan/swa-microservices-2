package com.example.accountservice.service;

import com.example.accountservice.entity.Account;
import com.example.commonmodule.dtos.AccountDto;

public interface AccountService {
    public AccountDto saveAccount(Account account);

    public AccountDto getAccountByUserId(Long userId);
}
