package com.example.commonmodule.dtos;

import com.example.commonmodule.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDto {
    private String shippingId;
    private Double shippingCode;
    private String orderId;
    public Status status;
}
