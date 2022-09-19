package com.example.orderservice.client;


import com.example.orderservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable String id);
}
