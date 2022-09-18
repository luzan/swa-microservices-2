package com.example.orderservice.controller;


import com.example.orderservice.client.ProductFeignClient;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entities.Order;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("check-product/{productId}")
    public ResponseEntity<String> checkProductPrice(@PathVariable String productId) throws JsonProcessingException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        Order createdOrder= orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.OK);
    }
}
