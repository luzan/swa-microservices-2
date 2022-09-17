package com.swa.productservice.dto;

import com.swa.productservice.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String id;
    private String productName;
    private Double price;
    private String vendor;
    private Category category;
    private Long quantity;
}
