package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entities.Order;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
}
