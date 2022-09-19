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

    @GetMapping("/stock/product/{productId}")
    public ResponseEntity<StockDto> getStockByProductId(@PathVariable String productId);

    @GetMapping("/stock/product/{id}/deallocate/{qty}")
    public ResponseEntity<?> updateStock(@PathVariable String id, @PathVariable Integer qty);
}
