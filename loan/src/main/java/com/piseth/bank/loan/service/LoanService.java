package com.piseth.bank.loan.service;

import java.util.List;

import com.piseth.bank.loan.entity.Loan;

public interface LoanService {
	Loan save(Loan loan);
	
	List<Loan> getList();
	
	List<Loan> getByCustomerId(Long customerId);
}
