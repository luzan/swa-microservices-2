package com.example.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountFeignClient {

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable String id);
}
