package com.example.orderservice.client;

import com.example.orderservice.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "STOCK-SERVICE")
public interface StockFeignClient {

    @GetMapping("/stocks/{productId}")
    public ResponseEntity<?> getStockByProductId(@PathVariable String productId);

    @PutMapping("/stocks/update")
    public ResponseEntity<?> updateStock(@RequestBody StockDto stockDto);
}
