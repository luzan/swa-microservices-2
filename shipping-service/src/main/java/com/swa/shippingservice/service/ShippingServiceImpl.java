package com.swa.shippingservice.service;

import com.example.commonmodule.dtos.ShippingDto;
import com.swa.shippingservice.entity.Shipping;
import com.swa.shippingservice.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    ShippingRepository shippingRepository;

    @Override
    public ShippingDto save(ShippingDto shippingDto) {
        if (shippingDto != null) {
            Shipping shipping = Shipping.builder()
                    .orderId(shippingDto.getOrderId())
                    .status(shippingDto.getStatus())
                    .shippingCode(shippingDto.getShippingCode())
                    .build();
            Shipping saveShipping = shippingRepository.save(shipping);

            return null;
        } else  {
            throw new RuntimeException();
        }
    }
}
