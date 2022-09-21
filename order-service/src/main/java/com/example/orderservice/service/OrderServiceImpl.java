package com.example.orderservice.service;

import com.example.commonmodule.dtos.AccountDto;
import com.example.commonmodule.dtos.ShippingDto;
import com.example.commonmodule.enums.Status;
import com.example.commonmodule.security.AppSecurityUtils;
import com.example.orderservice.client.*;
import com.example.orderservice.dto.*;
import com.example.orderservice.entities.Order;
import com.example.orderservice.enums.PaymentType;
import com.example.orderservice.repository.OrderLineRepository;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private StockFeignClient stockFeignClient;

    @Autowired
    private TransactionFeignClient transactionFeignClient;

    @Autowired
    private BankFeignClient bankFeignClient;

    @Autowired
    private PayPalFeignClient payPalFeignClient;

    @Autowired
    private CreditCardFeignClient creditCardFeignClient;

    @Autowired
    private ShippingFeignClient shippingFeignClient;

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        List<Double> productCost = orderDto.getProductList().stream().map(productDto -> {
            ResponseEntity<ProductDto> response = productFeignClient.getProductById(productDto.getProductId());
            ProductDto orderedProduct = response.getBody();
            if (productDto.getQuantity() > orderedProduct.getQuantity()) {
                throw new RuntimeException("Product " + productDto.getProductName() + " out of stock!!!");
            } else {
                StockDto stockDto = stockFeignClient.getStockByProductId(productDto.getProductId()).getBody();
                stockDto.setQuantity(Math.abs(orderedProduct.getQuantity() - productDto.getQuantity()));
                stockFeignClient.updateStock(productDto.getProductId(), productDto.getQuantity());
            }
            return productDto.getPrice() * productDto.getQuantity();
        }).collect(Collectors.toList());

        Double cost = calculateTotal(productCost);
        PaymentType paymentType = orderDto.getPaymentType();
        Long userId = AppSecurityUtils.getCurrentUserId().orElse(null);
        ResponseEntity<AccountDto> accountResponse = accountFeignClient.getAccountByUserId(userId.toString());
        Order order = Order.builder()
                .accountId(accountResponse.getBody().getAccountId())
                .paymentType(orderDto.getPaymentType())
                .productList(orderDto.getProductList())
                .build();

        Order savedOrder = orderRepository.save(order);
//        orderLineRepository.saveAll(savedOrder.getProductList());
        payment(cost, orderDto);

        shippedOrder(savedOrder.getId());

        return orderDto;
    }

    private void shippedOrder(String orderId) {
        ShippingDto shippingDto = ShippingDto.builder()
                .status(Status.SHIPPED)
                .shippingCode(Math.random())
                .orderId(orderId)
                .build();

        shippingFeignClient.createShipping(shippingDto);
    }

    private void payment(Double cost, OrderDto order) {
        PaymentType paymentType = order.getPaymentType();

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionCode(Math.random());
        transactionDto.setPaymentMethod(order.getPaymentType().toString());
        transactionDto.setTotal(cost);
        transactionDto.setOrderId(order.getId());

        if (paymentType == PaymentType.PAYPAL) {
            PaypalDto paypalDto = order.getPaypal();
            paypalDto.setBalance(cost);
            ResponseEntity<Boolean> response = payPalFeignClient.verifyPaypal(paypalDto);
            if (response.getBody() == false) {
                throw new RuntimeException("Invalid Paypal Account !!!");
            }
        } else if (paymentType == PaymentType.BANK) {
            BankAccountDto bankAccountDto = order.getBankAccount();
            bankAccountDto.setBalance(cost);
            ResponseEntity<Boolean> response = bankFeignClient.verifyPurchase(order.getBankAccount());
            if (response.getBody() == false) {
                throw new RuntimeException("Invalid Bank Account !!!");
            }
        } else if (paymentType == PaymentType.CREDIT_CARD) {
            CreditCardDto creditCardDto = order.getCreditCard();
            creditCardDto.setBalance(cost);
            ResponseEntity<Boolean> isCreditCardVerify = creditCardFeignClient.verifyPurchase(order.getCreditCard());
            if (isCreditCardVerify.getBody() == false) {
                throw new RuntimeException("Invalid Credit Card !!!");
            }
        } else {
            throw new RuntimeException("Invalid Payment Type !!!");
        }

        ResponseEntity<TransactionDto> transactionDtoResponse = transactionFeignClient.createTransaction(transactionDto);

    }

    private static Double calculateTotal(List<Double> productCost) {
        Double cost = 0.0;
        for (Double aDouble : productCost) {
            cost = cost + aDouble;
        }
        return cost;
    }
}
