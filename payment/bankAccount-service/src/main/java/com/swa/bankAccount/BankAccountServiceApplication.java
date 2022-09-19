package com.swa.bankAccount;

import com.swa.bankAccount.constant.AccountType;
import com.swa.bankAccount.model.BankAccount;
import com.swa.bankAccount.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class BankAccountServiceApplication implements CommandLineRunner {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loadData();
	}

	public void loadData() {

		List<BankAccount> bankAccounts = Arrays.asList(
				BankAccount.builder()
						.firstName("Supriya")
						.lastName("Ghising")
						.bankAccountNumber("12345678")
						.accountType(AccountType.CHECKING)
						.email("supriya.ghising@miu.edu")
						.routingNumber("11111")
						.balance(10000000.0)
						.build(),
				BankAccount.builder()
						.firstName("Anna")
						.lastName("Purna")
						.bankAccountNumber("11244343")
						.accountType(AccountType.SAVING)
						.email("anna.purna@gmail.com")
						.routingNumber("22222")
						.balance(111000.0)
						.build(),
				BankAccount.builder()
						.firstName("Pritha")
						.lastName("Sharma")
						.bankAccountNumber("1112223345")
						.accountType(AccountType.SAVING)
						.email("pritha.sharma@gmail.com")
						.routingNumber("33333")
						.balance(222200.0)
						.build());
		bankAccountRepository.saveAll(bankAccounts);
	}


}
