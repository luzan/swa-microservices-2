package com.swa.bankAccount.dto.mapper;

import com.swa.bankAccount.dto.BankAccountDto;
import com.swa.bankAccount.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public BankAccount mapToBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = BankAccount.builder()
                .firstName(bankAccountDto.getFirstName())
                .lastName(bankAccountDto.getLastName())
                .routingNumber(bankAccountDto.getRoutingNumber())
                .balance(bankAccountDto.getBalance())
                .email(bankAccountDto.getEmail())
                .accountType(bankAccountDto.getAccountType())
                .bankAccountNumber(bankAccountDto.getBankAccountNumber())
                .build();
        return bankAccount;
    }

    public BankAccountDto mapToDto(BankAccount bankAccount) {
        BankAccountDto bankAccountDto = BankAccountDto.builder()
                .balance(bankAccount.getBalance())
                .firstName(bankAccount.getFirstName())
                .lastName(bankAccount.getLastName())
                .routingNumber(bankAccount.getRoutingNumber())
                .email(bankAccount.getEmail())
                .accountType(bankAccount.getAccountType())
                .bankAccountNumber(bankAccount.getBankAccountNumber())
                .build();
        return bankAccountDto;
    }


}
