package com.piseth.bank.account.service.impl;

import java.util.List;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.piseth.bank.account.dto.CustomerMessageDTO;
import com.piseth.bank.account.entity.Customer;
import com.piseth.bank.account.repository.CustomerRepository;
import com.piseth.bank.account.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	private final CustomerRepository customerRepository;
	private final StreamBridge streamBridge;

	@Override
	public Customer save(Customer customer) {
		 customer = customerRepository.save(customer);
		 sendCommunication(customer);
		 return customer;
	}
	
	private void sendCommunication(Customer customer) {
		CustomerMessageDTO customerMessageDTO = new CustomerMessageDTO(customer.getCustomerId(), customer.getName(), customer.getEmail(), customer.getMobileNumber());
		log.info("Sending communication request for the details: {}",customerMessageDTO);
		var result = streamBridge.send("sendCommunication-out-0", customerMessageDTO);
		log.info("Is the communication request successfully triggered?: {}",result);
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(Long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Customer not found"));
	}

	public void updateCustomerCommunication(Long id){
		Customer customer = getById(id);
		customer.setCommunicationAlreadySent(true);
		customerRepository.save(customer);
	}

}
