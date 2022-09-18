package com.swa.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {
//    public String productId;
//    public Integer quantity;

    private String upc;
    private Integer quantityOnHand;
}
