package com.swa.creditCard.dto.mapper;

import com.swa.creditCard.dto.CreditCardDto;
import com.swa.creditCard.model.CreditCard;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Mapper {

    public CreditCard mapToCreditCard(CreditCardDto creditCardDto) {
        CreditCard creditCard = CreditCard.builder()
                .firstName(creditCardDto.getFirstName())
                .lastName(creditCardDto.getLastName())
                .cardNumber(creditCardDto.getCardNumber())
                .ccv(creditCardDto.getCcv())
                .build();
        creditCard.setExpiryDate(LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        creditCard.setCardLimit(2000.0);
        return creditCard;
    }

    public CreditCardDto mapToDto(CreditCard creditCard) {
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .firstName(creditCard.getFirstName())
                .lastName(creditCard.getLastName())
                .cardNumber(creditCard.getCardNumber())
                .ccv(creditCard.getCcv())
                .expiryDate(creditCard.getExpiryDate())
                .build();
        return creditCardDto;
    }
}
