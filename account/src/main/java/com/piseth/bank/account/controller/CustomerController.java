package com.piseth.bank.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.bank.account.dto.CardResponseDTO;
import com.piseth.bank.account.dto.CustomerDTO;
import com.piseth.bank.account.dto.CustomerDetailDTO;
import com.piseth.bank.account.dto.LoanResponseDTO;
import com.piseth.bank.account.entity.Customer;
import com.piseth.bank.account.mapper.CustomerMapper;
import com.piseth.bank.account.service.CustomerService;
import com.piseth.bank.account.service.client.CardFeignClient;
import com.piseth.bank.account.service.client.LoanFeignClient;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CardFeignClient cardFeignClient;
	
	@Autowired
	private LoanFeignClient loanFeignClient;
	
	@PostMapping
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO dto){
		Customer customer = customerMapper.toCustomer(dto);
		customer = customerService.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping
	public ResponseEntity<?> getCustomers(){
		return ResponseEntity.ok(customerService.getCustomers());
	}
	
	@GetMapping("{customerId}")
	public ResponseEntity<?> getCustomers(@PathVariable Long customerId){
		return ResponseEntity.ok(customerService.getById(customerId));
	}
	
	//@CircuitBreaker(name = "customerDetailSupport", fallbackMethod = "getCustomerDetailDefault")
	//@Retry(name = "retryCustomerDetail", fallbackMethod = "getCustomerDetailDefault")
	
	@GetMapping("customerDetail/{myCustomerId}")
	public ResponseEntity<CustomerDetailDTO> getCustomerDetail(
			@RequestHeader("pisethbank-correlation-id") String correlationId,
			@PathVariable("myCustomerId") Long customerId){
		//System.out.println("=========== ++Account Service++ ==============");
		//log.debug("Correlation id found: {}", correlationId);
		log.debug("fetchCustomerDetail method start");
		
		CustomerDetailDTO dto = new CustomerDetailDTO();
		Customer customer = customerService.getById(customerId);
		if(customer == null) {
			throw new RuntimeException("No customer found with this id");
		}
		CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
		
		List<LoanResponseDTO> loanInfo = loanFeignClient.getLoanInfo(correlationId, customerId);
		List<CardResponseDTO> cardInfo = cardFeignClient.getCardInfo(correlationId, customerId);
		
		dto.setCustomer(customerDTO);
		dto.setLoans(loanInfo);
		dto.setCards(cardInfo);
		
		log.debug("fetchCustomerDetail method end");
		return ResponseEntity.ok(dto);
	}
	
	public ResponseEntity<CustomerDetailDTO> getCustomerDetailDefault(@PathVariable("myCustomerId") Long customerId, Throwable e){
		CustomerDetailDTO dto = new CustomerDetailDTO();
		Customer customer = customerService.getById(customerId);
		if(customer == null) {
			throw new RuntimeException("No customer found with this id");
		}
		CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
		dto.setCustomer(customerDTO);
		return ResponseEntity.ok(dto);
	}
	
	
	@GetMapping("/sayHello")
	@RateLimiter(name = "sayHelloLimiter", fallbackMethod = "sayHi")
	public String sayHello() {
		return "Hello, welcome to PisethBank";
	}
	
	public String sayHi(Throwable t) {
		return "Hi, welcome to PisethBank";
	}

}
