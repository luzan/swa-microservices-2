package com.example.orderservice.service;

import com.example.orderservice.client.*;
import com.example.orderservice.dto.*;
import com.example.orderservice.entities.Order;
import com.example.orderservice.entities.OrderLine;
import com.example.orderservice.enums.AccountType;
import com.example.orderservice.enums.PaymentType;
import com.example.orderservice.repository.OrderLineRepository;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    StockFeignClient stockFeignClient;

    @Autowired
    TransactionFeignClient transactionFeignClient;

    @Autowired
    BankFeignClient bankFeignClient;

    @Autowired
    PayPalFeignClient payPalFeignClient;

    @Autowired
    CreditCardFeignClient creditCardFeignClient;

    @Override
    public Order createOrder(Order order) {
        List<Double> productCost = order.getProductList().stream().map(productDto -> {
            ResponseEntity<ProductDto> response = productFeignClient.getProductById(productDto.getProductId());
            ProductDto orderedProduct =  response.getBody();
                if (productDto.getQuantity() > orderedProduct.getQuantity()) {
                    throw new RuntimeException("Product "+ productDto.getProductName() + " out of stock!!!");
                } else {
                    StockDto stockDto = stockFeignClient.getStockByProductId(productDto.getProductId()).getBody();
                    stockDto.setQuantity(Math.abs(orderedProduct.getQuantity() - productDto.getQuantity()));
                    stockFeignClient.updateStock(productDto.getProductId(), productDto.getQuantity());
                }
                return productDto.getPrice() * productDto.getQuantity();
        }).collect(Collectors.toList());

        Double cost = calculateTotal(productCost);
        PaymentType paymentType = order.getPaymentType();
        Order savedOrder = orderRepository.save(order);
//        orderLineRepository.saveAll(savedOrder.getProductList());
        payment(cost, paymentType, savedOrder.getId());

        return order;
    }

    private void payment(Double cost, PaymentType paymentType, String orderId) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionCode(Math.random());
        transactionDto.setPaymentMethod(paymentType.toString());
        transactionDto.setTotal(cost);
        transactionDto.setOrderId(orderId);

        if (paymentType == PaymentType.PAYPAL) {
            PaypalDto paypalDto = new PaypalDto();
            paypalDto.setFirstName("Simran");
            paypalDto.setLastName("Sthapit");
            paypalDto.setEmailAddress("ss@gmail");
            paypalDto.setBalance(3000.0);
            paypalDto.setSecureKey("2118");
            ResponseEntity<Boolean> response = payPalFeignClient.verifyPaypal(paypalDto);
            if (response.getBody()) {
                ResponseEntity<TransactionDto> transactionDtoResponse = transactionFeignClient.createTransaction(transactionDto);
            } else {
                throw new RuntimeException("Invalid Paypal Account !!!");
            }
        } else if (paymentType == PaymentType.BANK) {
            BankAccountDto bankAccountDto = new BankAccountDto();
            bankAccountDto.setFirstName("Sanjaya");
            bankAccountDto.setLastName("Koju");
            bankAccountDto.setAccountType(AccountType.CHECKING);
            bankAccountDto.setEmail("sanjayakoju@gmail.com");
            bankAccountDto.setBankAccountNumber("23235235");
            bankAccountDto.setRoutingNumber("014215");
            bankAccountDto.setBalance(cost);
            ResponseEntity<Boolean> response = bankFeignClient.verifyPurchase(bankAccountDto);
            if (response.getBody()) {
                ResponseEntity<TransactionDto> transactionDtoResponse = transactionFeignClient.createTransaction(transactionDto);
            } else {
                throw new RuntimeException("Invalid Bank Account !!!");
            }
        } else if (paymentType == PaymentType.CREDIT_CARD) {
            CreditCardDto creditCardDto = new CreditCardDto();
            creditCardDto.setFirstName("Anthony");
            creditCardDto.setLastName("Sander");
            creditCardDto.setCardLimit(2000.0);
            creditCardDto.setCcv("4321");
            creditCardDto.setExpiryDate(LocalDate.of(2024,11,11));
            creditCardDto.setBalance(0.0);
            creditCardDto.setCardNumber("123456789");
            ResponseEntity<Boolean> isCreditCardVerify = creditCardFeignClient.verifyPurchase(creditCardDto);
            if (isCreditCardVerify.getBody()) {
                ResponseEntity<TransactionDto> transactionDtoResponse = transactionFeignClient.createTransaction(transactionDto);
            } else {
                throw new RuntimeException("Invalid Credit Card !!!");
            }
        } else {
            throw new RuntimeException("Invalid Payment Type !!!");
        }

    }

    private static Double calculateTotal(List<Double> productCost) {
        Double cost = 0.0;
        for (Double aDouble : productCost) {
            cost = cost + aDouble;
        }
        return  cost;
    }
}
