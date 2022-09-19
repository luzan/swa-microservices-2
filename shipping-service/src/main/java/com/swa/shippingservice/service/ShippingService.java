package com.swa.shippingservice.service;

import com.example.commonmodule.dtos.ShippingDto;
import com.swa.shippingservice.entity.Shipping;

public interface ShippingService {
    public ShippingDto save(ShippingDto shippingDto);
}
