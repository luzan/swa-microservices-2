package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {

    private String id;
    private String productId;
    private Long quantity;
}
