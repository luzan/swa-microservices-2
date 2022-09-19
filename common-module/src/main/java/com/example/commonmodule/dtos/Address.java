package com.example.commonmodule.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String addressId;
    private String country;
    private String state;
    private String city;
    private  String street;
    private String zip;
}
