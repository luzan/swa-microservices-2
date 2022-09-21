package com.swa.productservice.client;

import com.swa.productservice.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "STOCK-SERVICE", url = "${app.url.stock-service}")
public interface StockFeignClient {

    @PostMapping("/stock/create-stock")
    public ResponseEntity<?> createStock(@RequestBody StockDto stockDto);

    @GetMapping("/stock/product/{productId}")
    public ResponseEntity<StockDto> getStockByProductId(@PathVariable String productId);
}
