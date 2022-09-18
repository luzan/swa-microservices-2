package com.swa.bankAccount.repository;

import com.swa.bankAccount.constant.AccountType;
import com.swa.bankAccount.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    Optional<BankAccount> findBankAccountByAccountTypeAndRoutingNumberAndBankAccountNumber(AccountType accountType, String routingNumber, String bankAccountNumber);
}
