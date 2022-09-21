package com.example.orderservice.controller;


import com.example.orderservice.client.ProductFeignClient;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.ProductDto;
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

    @Autowired
    private ProductFeignClient productFeignClient;

    @GetMapping("check-product/{productId}")
    public ResponseEntity<?> checkProductPrice(@PathVariable String productId) throws JsonProcessingException {
        ProductDto testProduct = ProductDto.builder()
                .id("123-random-string-id-000")
                .productName("testting order api")
                .quantity(1)
                .price(100.0)
                .build();
        ResponseEntity<ProductDto> foundproduct = productFeignClient.getProductById("123");
        System.out.println(foundproduct.getStatusCode());
        return new ResponseEntity<>(testProduct , HttpStatus.OK);
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder= orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.OK);
    }
}
