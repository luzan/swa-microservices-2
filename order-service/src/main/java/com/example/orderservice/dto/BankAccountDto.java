package com.example.orderservice.dto;


import com.example.orderservice.enums.AccountType;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccountDto {

    private String routingNumber;
    private String bankAccountNumber;
    private AccountType accountType;
    private Double balance;
}
