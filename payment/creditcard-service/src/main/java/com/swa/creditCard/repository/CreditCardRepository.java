package com.swa.creditCard.repository;

import com.swa.creditCard.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    Optional<CreditCard> findCreditCardByCardNumberAndExpiryDateAndCcv(String cardNumber, LocalDate expiryDate, String ccv);
}
