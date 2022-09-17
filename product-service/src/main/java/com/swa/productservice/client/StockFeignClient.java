package com.swa.productservice.client;

import com.swa.productservice.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "STOCK-SERVICE")
public interface StockFeignClient {

    @PostMapping("/stocks/create-stock")
    public ResponseEntity<?> createStock(@RequestBody StockDto stockDto);

    @GetMapping("/stocks/{productId}")
    public ResponseEntity<?> getStockByProductId(@PathVariable String productId);
}
