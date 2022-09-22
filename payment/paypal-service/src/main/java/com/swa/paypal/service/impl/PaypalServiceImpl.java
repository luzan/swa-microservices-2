package com.swa.paypal.service.impl;

import com.swa.paypal.dto.PaypalDto;
import com.swa.paypal.dto.mapper.Mapper;
import com.swa.paypal.model.Paypal;
import com.swa.paypal.repository.PaypalRepository;
import com.swa.paypal.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Autowired
    private PaypalRepository paypalRepo;
    @Autowired
    private Mapper mapper;

    public PaypalDto savePaypal(PaypalDto paypalDto) {
        Paypal paypal = mapper.mapToPaypal(paypalDto);
        paypalRepo.save(paypal);
        return paypalDto;
    }

    public boolean checkPaypal(PaypalDto paypalDto) {
        Optional<Paypal> paypalOpt = paypalRepo.findByEmailAddressAndSecureKey(paypalDto.getEmailAddress(), paypalDto.getSecureKey());
        if(!validatePaypal(paypalOpt, paypalDto)) return false;
        return updateChanges(paypalOpt.get(), paypalDto);
    }

    private boolean validatePaypal(Optional<Paypal> paypalOpt, PaypalDto paypalDto) {
        if (paypalOpt.isEmpty()) {
            System.out.println("Invalid account !!");
            return false;
        }

        if (paypalOpt.get().getBalance() - paypalDto.getBalance() < 0) {
            System.out.println("Insufficient balance to purchase item.");
            return false;
        }

        return true;
    }

    private boolean updateChanges(Paypal paypal, PaypalDto paypalDto) {
        Double balance = paypal.getBalance() - paypalDto.getBalance();
        paypal.setBalance(balance);
        paypalRepo.save(paypal);
        return true;
    }


}
