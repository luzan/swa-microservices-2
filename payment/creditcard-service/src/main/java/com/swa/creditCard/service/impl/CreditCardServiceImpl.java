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

    public boolean checkCreditCard(CreditCardDto creditCardDto) {
        Optional<CreditCard> creditCardOpt= creditCardRepo.findCreditCardByCardNumberAndExpiryDateAndCcv(creditCardDto.getCardNumber(), creditCardDto.getExpiryDate(), creditCardDto.getCcv());

        if(!validateCreditCard(creditCardOpt, creditCardDto)) return false;
        return updateChanges(creditCardOpt.get(), creditCardDto);

    }

    private boolean validateCreditCard(Optional<CreditCard> creditCardOpt, CreditCardDto creditCardDto) {

        if(!creditCardOpt.isPresent()) {
            System.out.println("Invalid card !!");
            return false;
        }

        CreditCard creditCard = creditCardOpt.get();
        if(creditCard.getExpiryDate().isBefore(LocalDate.now())) {
            System.out.println("Card has expired.");
            return  false;
        }

        if(creditCard.getCardLimit()< creditCardDto.getBalance() + creditCard.getBalance()) {
            System.out.println("Limitation of card has been exceeded.");
            return false;
        }
        return true;
    }

    private boolean updateChanges(CreditCard creditCard, CreditCardDto creditCardDto) {
        Double totalNewBalance=  creditCardDto.getBalance() + creditCard.getBalance();
        creditCard.setBalance(totalNewBalance);
        creditCardRepo.save(creditCard);
        return true;

    }

}
