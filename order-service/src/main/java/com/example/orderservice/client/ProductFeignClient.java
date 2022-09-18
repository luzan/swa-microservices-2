package com.example.orderservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "product-service")
public interface ProductFeignClient {

    @RequestMapping(value = "stocks/{productId}", method = RequestMethod.GET)
    ResponseEntity<?> getProductStockById(@PathVariable String id);
}
