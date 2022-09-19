package com.swa.paypal.dto.mapper;

import com.swa.paypal.dto.PaypalDto;
import com.swa.paypal.model.Paypal;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public PaypalDto mapToDto(Paypal paypal) {
        PaypalDto paypalDto = PaypalDto.builder()
                .firstName(paypal.getFirstName())
                .lastName(paypal.getLastName())
                .balance(paypal.getBalance())
                .emailAddress(paypal.getEmailAddress())
                .secureKey(paypal.getSecureKey())
                .build();
        return paypalDto;
    }

    public Paypal mapToPaypal(PaypalDto paypalDto) {
        Paypal paypal = Paypal.builder()
                .firstName(paypalDto.getFirstName())
                .lastName(paypalDto.getLastName())
                .emailAddress(paypalDto.getEmailAddress())
                .secureKey(paypalDto.getSecureKey())
                .balance(paypalDto.getBalance())
                .build();
        return paypal;
    }
}
