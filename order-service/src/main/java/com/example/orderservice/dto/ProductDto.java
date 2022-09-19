package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private String id;
    private String productName;
    private Double price;
    private String vendor;
    private Integer quantity;
}
