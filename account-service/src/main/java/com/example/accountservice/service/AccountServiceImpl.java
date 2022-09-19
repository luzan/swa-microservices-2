package com.example.accountservice.service;

import com.example.accountservice.entity.Account;
import com.example.accountservice.repository.AccountRepository;
import com.example.commonmodule.dtos.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public AccountDto saveAccount(Account account) {
        if (account != null) {
            Account savedAccount = accountRepository.save(account);
            AccountDto accountDto = AccountDto.builder()
                    .accountId(savedAccount.getAccountId())
                    .userId(savedAccount.getUserId())
                    .firstName(savedAccount.getFirstName())
                    .lastName(savedAccount.getLastName())
                    .build();
            return accountDto;
        } else {
            throw new RuntimeException("Account Detail is null");
        }

    }

    @Override
    public AccountDto getAccountByUserId(String userId) {
        Account account = accountRepository.findAccountByUserId(userId);
        if (account != null) {
            AccountDto accountDto = AccountDto.builder()
                    .accountId(account.getAccountId())
                    .userId(account.getUserId())
                    .firstName(account.getFirstName())
                    .lastName(account.getLastName())
                    .build();
            return accountDto;
        } else {
            throw new RuntimeException("Account Not Found !!!");
        }

    }
}
