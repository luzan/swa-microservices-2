package com.swa.productservice.service;

import com.swa.productservice.client.StockFeignClient;
import com.swa.productservice.dto.ProductDto;
import com.swa.productservice.dto.StockDto;
import com.swa.productservice.entities.Product;
import com.swa.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockFeignClient stockFeignClient;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .productName(productDto.getProductName())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .vendor(productDto.getVendor())
                .build();

        Product savedProduct = productRepository.save(product);

        StockDto stockDto = StockDto.builder()
                .productId(product.getId())
                .quantity(productDto.getQuantity())
                .build();

        stockFeignClient.createStock(stockDto);

        ProductDto savedProductDto = ProductDto.builder()
                .id(savedProduct.getId())
                .productName(savedProduct.getProductName())
                .price(savedProduct.getPrice())
                .category(savedProduct.getCategory())
                .vendor(savedProduct.getVendor())
                .quantity(stockDto.getQuantity())
                .build();

        return savedProductDto;
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> productList =  productRepository.findAll();
        return productList;
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = productRepository.findById(id).get();

        StockDto stockDto = (StockDto) stockFeignClient.getStockByProductId(id).getBody();

        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .category(product.getCategory())
                .vendor(product.getVendor())
                .quantity(stockDto.getQuantity())
                .build();
        return productDto;
    }
}
