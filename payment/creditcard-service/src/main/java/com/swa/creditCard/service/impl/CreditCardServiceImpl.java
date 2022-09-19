package com.swa.creditCard.service.impl;

import com.swa.creditCard.dto.CreditCardDto;
import com.swa.creditCard.dto.mapper.Mapper;
import com.swa.creditCard.model.CreditCard;
import com.swa.creditCard.repository.CreditCardRepository;
import com.swa.creditCard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepo;

    @Autowired
    private Mapper mapper;

    @Override
    public CreditCardDto saveCreditCard(CreditCardDto creditCardDto) {
        CreditCard creditCard= mapper.mapToCreditCard(creditCardDto);
        creditCardRepo.save(creditCard);

        creditCardDto.setExpiryDate(creditCard.getExpiryDate());
        creditCardDto.setCardLimit(creditCard.getCardLimit());
        return creditCardDto;
    }

    public boolean verifyPurchase(CreditCardDto creditCardDto) {
        Optional<CreditCard> creditCardOpt= creditCardRepo.findCreditCardByCardNumberAndExpiryDateAndCcv(creditCardDto.getCardNumber(), creditCardDto.getExpiryDate(), creditCardDto.getCcv());

        if(!creditCardOpt.isPresent()) {
            System.out.println("Invalid card");
            return false;
        }

        CreditCard creditCard = creditCardOpt.get();
        LocalDate cardExpiryDate= creditCard.getExpiryDate();
        LocalDate todayDate= LocalDate.now();
        if(cardExpiryDate.isBefore(todayDate)) {
            System.out.println("Card has expired");
            return  false;
        }

        Double purchaseBalance= creditCardDto.getBalance();
        Double totalNewBalance=  purchaseBalance + creditCard.getBalance();
        if(creditCard.getCardLimit()< totalNewBalance) {
            System.out.println("Limitation of card has been exceed");
            return false;
        }
        creditCard.setBalance(totalNewBalance);
        creditCardRepo.save(creditCard);
        return true;

    }

}
