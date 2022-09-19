package com.swa.paypal;

import com.swa.paypal.model.Paypal;
import com.swa.paypal.repository.PaypalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class PaypalServiceApplication implements CommandLineRunner {

	@Autowired
	private PaypalRepository paypalRepository;

	public static void main(String[] args) {
		SpringApplication.run(PaypalServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loadData();
	}

	public void loadData() {

		List<Paypal> paypalList = Arrays.asList(Paypal.builder()
						.firstName("Simran")
						.lastName("Sthapit")
						.emailAddress("ss@gmail")
						.balance(3000.0)
						.secureKey("2118")
						.build(),
				Paypal.builder()
						.firstName("Ruby")
						.lastName("Shah")
						.emailAddress("rs@gmail")
						.balance(500.0)
						.secureKey("2399")
						.build(),
				Paypal.builder()
						.firstName("Ram")
						.lastName("kc")
						.emailAddress("ram@gmail")
						.balance(1500.0)
						.secureKey("1111")
						.build());

		paypalRepository.saveAll(paypalList);
		System.out.println("paypal accounts has been successfully save");

	}
}
