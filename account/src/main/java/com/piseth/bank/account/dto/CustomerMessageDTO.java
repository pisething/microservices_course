package com.piseth.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMessageDTO {
	private Long customerId;
	private String name;
	private String email;
	private String mobileNumber;
}
