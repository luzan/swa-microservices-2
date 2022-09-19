package com.swa.productservice.service;

import com.swa.productservice.client.StockFeignClient;
import com.swa.productservice.dto.ProductDto;
import com.swa.productservice.dto.StockDto;
import com.swa.productservice.entities.Product;
import com.swa.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

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
    public List<ProductDto> findAllProduct() {
      return productRepository.findAll()
              .stream()
              .map(product -> {
                  ProductDto productDto = new ProductDto();
                  productDto.setId(product.getId());
                  productDto.setProductName(product.getProductName());
                  productDto.setVendor(product.getVendor());
                  productDto.setPrice(product.getPrice());
                  productDto.setCategory(product.getCategory());
                  ResponseEntity<?> response = stockFeignClient.getStockByProductId(product.getId());
                  StockDto stockDto = (StockDto) response.getBody();
                  if (stockDto != null) {
                      productDto.setQuantity(stockDto.getQuantity());
                  }
                  return productDto;
              }).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = productRepository.findById(id).get();

        ResponseEntity<?> response = stockFeignClient.getStockByProductId(id);

        StockDto stockDto = (StockDto) response.getBody();

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
