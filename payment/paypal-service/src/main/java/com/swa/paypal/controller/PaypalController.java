package com.swa.paypal.controller;

import com.swa.paypal.dto.PaypalDto;
import com.swa.paypal.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paypals")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    @PostMapping
    public ResponseEntity<?> savePaypal(@RequestBody PaypalDto paypalDto){
        ResponseEntity<?> response= new ResponseEntity<>(paypalService.savePaypal(paypalDto), HttpStatus.OK);
        return response;
    }


    @PutMapping("verify-purchase")
    public ResponseEntity<?> checkPaypal(@RequestBody PaypalDto paypalDto){
        return new ResponseEntity<>(paypalService.checkPaypal(paypalDto), HttpStatus.OK);
    }


}
