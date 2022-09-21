package com.example.orderservice.dto;

import com.example.orderservice.entities.OrderLine;
import com.example.orderservice.enums.PaymentType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {

    private String id;
    private List<OrderLine> productList;
    private PaymentType paymentType;
    private BankAccountDto bankAccount;
    private CreditCardDto creditCard;
    private PaypalDto paypal;
}
