package com.swa.paypal.repository;

import com.swa.paypal.model.Paypal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaypalRepository extends JpaRepository<Paypal, Integer> {
    Optional<Paypal> findByEmailAddressAndSecureKey(String emailAddress, String secureKey);
}
