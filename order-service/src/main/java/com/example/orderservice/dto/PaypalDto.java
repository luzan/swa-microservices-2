package com.example.orderservice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaypalDto {
    private String emailAddress;
    private String secureKey;
    private Double balance;
}
