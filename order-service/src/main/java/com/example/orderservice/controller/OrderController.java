package com.example.orderservice.controller;


import com.example.orderservice.feign.ProductFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private ProductFeignClient productFeignClient;

    @GetMapping("check-product/{productId}")
    public ResponseEntity<String> checkProductPrice(@PathVariable Long productId) throws JsonProcessingException {
        ResponseEntity<?> foundProduct = productFeignClient.getProductById(productId);
        ObjectMapper objectMapper = new ObjectMapper();
        String productAsJsonString = objectMapper.writeValueAsString(foundProduct.getBody());
        return new ResponseEntity<>(productAsJsonString, HttpStatus.OK);
    }
}
