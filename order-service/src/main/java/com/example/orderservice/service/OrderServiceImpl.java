package com.example.orderservice.service;

import com.example.orderservice.client.ProductFeignClient;
import com.example.orderservice.client.StockFeignClient;
import com.example.orderservice.entities.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    StockFeignClient stockFeignClient;

    @Override
    public Order createOrder(Order order) {
//        List<Product> productList = orderDto.getProductList();
//        if (productList.isEmpty()) {
//            throw new RuntimeException("Product not available");
//        } else {
//            for (ProductDto productDto: productList) {
//                ProductDto orderedProduct = (ProductDto) productFeignClient.getProductStockById(productDto.getId()).getBody();
//                if (productDto.getQuantity() > orderedProduct.getQuantity()) {
//                    throw new RuntimeException("Product "+ productDto.getProductName() + " out of stock!!!");
//                } else {
//                    StockDto stockDto = (StockDto) stockFeignClient.getStockByProductId(orderedProduct.getId()).getBody();
//                    stockDto.setQuantity(Math.abs(orderedProduct.getQuantity() - productDto.getQuantity()));
//                    stockFeignClient.updateStock(stockDto);
//                }
//
//
//            }
//        }

        return orderRepository.save(order);
    }
}
