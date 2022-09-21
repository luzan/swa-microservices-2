package com.swa.paypal.service;

import com.swa.paypal.dto.PaypalDto;

public interface PaypalService {
    PaypalDto savePaypal(PaypalDto paypalDto);

    boolean checkPaypal(PaypalDto paypalDto);
}
