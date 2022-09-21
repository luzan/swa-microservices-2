package com.example.orderservice.client;

import com.example.orderservice.dto.PaypalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYPAL-SERVICE", url = "${app.url.paypal-service}")
public interface PayPalFeignClient {

    @PutMapping("/paypals/verify-purchase")
    public ResponseEntity<Boolean> verifyPaypal(@RequestBody PaypalDto paypalDto);

    @PostMapping("/paypals")
    public ResponseEntity<PaypalDto> savePaypal(@RequestBody PaypalDto paypalDto);
}
