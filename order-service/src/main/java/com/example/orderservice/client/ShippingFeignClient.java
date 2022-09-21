package com.example.orderservice.client;

import com.example.commonmodule.dtos.ShippingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SHIPPING-SERVICE", url = "${app.url.shipping-service}")
public interface ShippingFeignClient {

    @PostMapping("/shippings/create-shipping")
    public ResponseEntity<?> createShipping(@RequestBody ShippingDto shippingDto);
}
