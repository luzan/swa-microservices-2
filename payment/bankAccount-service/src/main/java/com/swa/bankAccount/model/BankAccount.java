package com.swa.bankAccount.model;

import com.swa.bankAccount.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String routingNumber;
    private String bankAccountNumber;
    private Double balance;

}
