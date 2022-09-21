package com.example.orderservice.client;

import com.example.commonmodule.dtos.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACCOUNT-SERVICE", url = "${app.url.account-service}")
public interface AccountFeignClient {

    @GetMapping("/accounts/user/{id}")
    public ResponseEntity<AccountDto> getAccountByUserId(@PathVariable String id);
}
