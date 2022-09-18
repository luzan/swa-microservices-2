package com.example.orderservice.dto;

import com.example.orderservice.entities.OrderLine;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {

    private String id;
    private String accountId;
    private List<OrderLine> productList;
}
