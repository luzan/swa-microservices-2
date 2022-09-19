package com.swa.creditCard.service;

import com.swa.creditCard.dto.CreditCardDto;

public interface CreditCardService {

    CreditCardDto saveCreditCard(CreditCardDto creditCardDto);
    boolean verifyPurchase(CreditCardDto creditCardDto);
}
