package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entities.Order;

public interface OrderService {

    Order createOrder(Order order);
}
