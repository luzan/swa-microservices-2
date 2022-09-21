package com.example.accountservice.repository;

import com.example.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AccountRepository extends JpaRepository<Account, String> {

    public Account findAccountByUserId(Long userId);
}
