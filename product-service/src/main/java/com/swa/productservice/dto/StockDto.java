package com.swa.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {

    public String id;
    public String productId;
    public Long quantity;
}
