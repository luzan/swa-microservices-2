package com.swa.productservice.service;

import com.swa.productservice.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();
}
