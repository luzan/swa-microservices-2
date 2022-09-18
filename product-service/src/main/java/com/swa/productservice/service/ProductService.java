package com.swa.productservice.service;

import com.swa.productservice.dto.ProductDto;
import com.swa.productservice.entities.Product;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> findAllProduct();
    ProductDto getProductById(String id);
}
