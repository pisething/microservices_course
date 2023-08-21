package com.piseth.bank.account.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.piseth.bank.account.entity.Account;
import com.piseth.bank.account.entity.Customer;
import com.piseth.bank.account.repository.AccountRepository;
import com.piseth.bank.account.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SetupAccountRunner implements CommandLineRunner{
	
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Customer customer = new Customer();
		customer.setCreateDate(LocalDate.now());
		customer.setEmail("piseth@gmail.com");
		customer.setMobileNumber("070363363");
		customer.setName("Piseth Ing");
		customerRepository.save(customer);
		
		Account account = new Account();
		account.setAccountNumber(1L);
		account.setAccountType("Saving");
		account.setBranchAddress("Phnom Penh");
		account.setCreateDate(LocalDate.now());
		account.setCustomer(customer);
		accountRepository.save(account);
		log.info("Account created");
	}

}
