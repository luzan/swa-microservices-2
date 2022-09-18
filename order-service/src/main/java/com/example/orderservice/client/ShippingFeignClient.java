package com.example.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "SHIPPING-SERVICE")
public interface ShippingFeignClient {

    @PostMapping("/shippings/create-shipping")
    public ResponseEntity<?> createShipping();
}
