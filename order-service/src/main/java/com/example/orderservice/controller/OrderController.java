package com.example.orderservice.controller;


import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.entities.Order;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
//@PreAuthorize("hasRole('ROLE_USER')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("check-product/{productId}")
    public ResponseEntity<?> checkProductPrice(@PathVariable String productId) throws JsonProcessingException {
        ProductDto testProduct = ProductDto.builder()
                .id("123-random-string-id-000")
                .productName("testting order api")
                .quantity(1)
                .price(100.0)
                .build();
        return new ResponseEntity<>(testProduct , HttpStatus.OK);
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        Order createdOrder= orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.OK);
    }
}
