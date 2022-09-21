package com.swa.shippingservice.controller;

import com.example.commonmodule.dtos.ShippingDto;
import com.swa.shippingservice.entity.Shipping;
import com.swa.shippingservice.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shippings")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @PostMapping("/create-shipping")
    public ResponseEntity<?> saveShipping(@RequestBody ShippingDto shippingDto) {
        return new ResponseEntity<>(shippingService.save(shippingDto), HttpStatus.OK);
    }
}
