package com.piseth.bank.message.function;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MessageFunctions {
	
	@Bean
	Function<CustomerMessageDTO, CustomerMessageDTO> email(){
		return customerMsgDTO ->{
			log.info("Sending email with this data: {}", customerMsgDTO);
			return customerMsgDTO;
		};
	}
	
	@Bean
	Function<CustomerMessageDTO, Long> sms(){
		return customerMsgDTO ->{
			log.info("Sending sms with this number: {}", customerMsgDTO);
			return customerMsgDTO.getCustomerId();
		};
	}

}
