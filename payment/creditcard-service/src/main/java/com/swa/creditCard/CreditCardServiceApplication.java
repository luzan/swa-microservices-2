package com.swa.creditCard;

import com.swa.creditCard.model.CreditCard;
import com.swa.creditCard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class CreditCardServiceApplication implements CommandLineRunner {

	@Autowired
	private CreditCardRepository creditCardRepo;

	public static void main(String[] args) {
		SpringApplication.run(CreditCardServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loadData();
	}

	public void loadData() {
		List<CreditCard> creditCards = Arrays.asList(CreditCard.builder()
						.firstName("Anthony")
						.lastName("Sander")
						.cardLimit(2000.0)
						.ccv("4321")
						.expiryDate(LocalDate.of(2024,11,11))
						.balance(0.0)
						.cardNumber("123456789").build(),
				CreditCard.builder()
						.firstName("Anna")
						.lastName("Purna")
						.cardLimit(2000.0)
						.ccv("2118")
						.expiryDate(LocalDate.of(2024,11,11))
						.balance(0.0)
						.cardNumber("111111111").build());

		creditCardRepo.saveAll(creditCards);
	}
}
