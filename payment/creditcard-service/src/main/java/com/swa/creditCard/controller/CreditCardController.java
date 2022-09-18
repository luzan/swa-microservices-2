package com.swa.creditCard.controller;

import com.swa.creditCard.dto.CreditCardDto;
import com.swa.creditCard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<?> saveCrditCard(@RequestBody CreditCardDto creditCardDto){
        ResponseEntity<?> response= new ResponseEntity<>(creditCardService.saveCreditCard(creditCardDto), HttpStatus.OK);
        return response;
    }


    @PostMapping("/verify-purchase")
    public ResponseEntity<?> verifyPurchase(@RequestBody CreditCardDto creditCardDto) {
        ResponseEntity<?> response = new ResponseEntity<>(creditCardService.verifyPurchase(creditCardDto), HttpStatus.OK);
        return response;
    }


}
