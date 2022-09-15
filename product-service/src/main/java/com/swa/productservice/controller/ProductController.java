package com.swa.productservice.controller;

import com.swa.productservice.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<ProductDto> productList = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setProductName("test prodcut");
        productList.add(productDto);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
