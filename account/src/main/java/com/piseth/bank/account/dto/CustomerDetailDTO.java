package com.piseth.bank.account.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerDetailDTO {
	private CustomerDTO customer;
	private List<CardResponseDTO> cards;
	private List<LoanResponseDTO> loans;
}
