package com.swa.bankAccount.service.impl;

import com.swa.bankAccount.dto.BankAccountDto;
import com.swa.bankAccount.dto.mapper.Mapper;
import com.swa.bankAccount.model.BankAccount;
import com.swa.bankAccount.repository.BankAccountRepository;
import com.swa.bankAccount.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public BankAccountDto save(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = mapper.mapToBankAccount(bankAccountDto);
        bankAccount = bankAccountRepository.save(bankAccount);
        return mapper.mapToDto(bankAccount);
    }

    @Override
    public Boolean checkBankAccount(BankAccountDto bankAccountDto) {
        Optional<BankAccount> bankAccountOpt = bankAccountRepository.findBankAccountByAccountTypeAndRoutingNumberAndBankAccountNumber(bankAccountDto.getAccountType(), bankAccountDto.getRoutingNumber(), bankAccountDto.getBankAccountNumber());
        if(!validateBankAccount(bankAccountOpt, bankAccountDto)) return false;
        return updateChanges(bankAccountOpt.get(),bankAccountDto);
    }

    private boolean validateBankAccount(Optional<BankAccount> bankAccountOpt, BankAccountDto bankAccountDto) {
        if(!bankAccountOpt.isPresent()){
            System.out.println("Invalid account !!");
            return false;
        }

        BankAccount bankAccount = bankAccountOpt.get();
        Double availableBalance = bankAccount.getBalance()-bankAccountDto.getBalance();
        if(availableBalance < 0){
            System.out.println("Insufficient balance to purchase item.");
            return false;
        }

        return true;
    }

    private boolean updateChanges(BankAccount bankAccount, BankAccountDto bankAccountDto) {
        Double availableBalance = bankAccount.getBalance()-bankAccountDto.getBalance();
        bankAccount.setBalance(availableBalance);
        bankAccountRepository.save(bankAccount);
        return true;
    }
}
