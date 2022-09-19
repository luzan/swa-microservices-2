package com.swa.productservice.controller;

import com.swa.productservice.client.StockFeignClient;
import com.swa.productservice.dto.ProductDto;
import com.swa.productservice.entities.Product;
import com.swa.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDto> allProduct = productService.findAllProduct();
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        ProductDto productDto = productService.getProductById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
