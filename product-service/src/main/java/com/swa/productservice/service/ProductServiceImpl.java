package com.swa.productservice.service;

import com.swa.productservice.dto.ProductDto;
import com.swa.productservice.entities.Product;
import com.swa.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProduct() {
        List<Product> productList =  productRepository.findAll();
        return productList;
    }

}
