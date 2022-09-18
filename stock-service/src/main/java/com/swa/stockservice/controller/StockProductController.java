package com.swa.stockservice.controller;

import com.swa.stockservice.model.ProductStockDto;
import com.swa.stockservice.service.ProductStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JR.
 */
@Slf4j
@RestController
@RequestMapping("/stock")
public class StockProductController {

    private final ProductStockService productStockService;

    public StockProductController(ProductStockService productStockService) {
        this.productStockService = productStockService;
    }

    @PostMapping("/create-stock")
    public ResponseEntity<?> createProductStock(@RequestBody ProductStockDto stockDto) {
        ProductStockDto createdStockdto = productStockService.createStock(stockDto);
        return new ResponseEntity<>(createdStockdto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> listStockProducts(){
        log.debug("Finding stock products ");
        return new ResponseEntity<>(productStockService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<?> productsByUpc(@PathVariable String id){
        log.debug("Finding stock by id:" + id);

        return new ResponseEntity<>(productStockService.productsByUpc(id), HttpStatus.OK);
    }

    @GetMapping("product/{id}/allocate/{qty}")
    public ResponseEntity<?> allocateQtyByUpc(@PathVariable String id, @PathVariable Integer qty){
        if(qty < 1){
            return new ResponseEntity<>("Quantity must be greater than zero", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productStockService.allocateByUpc(id, qty), HttpStatus.OK);
    }

    @GetMapping("product/{id}/deallocate/{qty}")
    public ResponseEntity<?> deAllocateQtyByUpc(@PathVariable String id, @PathVariable Integer qty){
        if(qty < 1){
            return new ResponseEntity<>("Quantity must be greater than zero", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productStockService.deallocateByUpc(id, qty), HttpStatus.OK);
    }
}
