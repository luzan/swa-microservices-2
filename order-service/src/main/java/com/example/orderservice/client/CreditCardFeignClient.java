package com.example.orderservice.client;

import com.example.orderservice.dto.CreditCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CREDIT-CARD-SERVICE", url = "${app.url.credit-service}")
public interface CreditCardFeignClient {

    @PostMapping("creditcards/verify-purchase")
    public ResponseEntity<Boolean> verifyPurchase(@RequestBody CreditCardDto creditCardDto);
}
