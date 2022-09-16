package com.swa.productservice.controller;

import com.swa.productservice.dto.ProductDto;
import com.swa.productservice.entities.Product;
import com.swa.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDto> productList = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setProductName("test prodcut");
        productList.add(productDto);

        List<Product> allProduct = productService.findAllProduct();

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName("test prodcut");
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
