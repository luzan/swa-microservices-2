package com.swa.bankAccount.service;

import com.swa.bankAccount.dto.BankAccountDto;

public interface BankAccountService {

    BankAccountDto save(BankAccountDto bankAccountDto);

    Boolean checkBankAccount(BankAccountDto bankAccountDto);
}
