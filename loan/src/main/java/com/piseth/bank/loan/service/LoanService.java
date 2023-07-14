package com.piseth.bank.loan.service;

import java.util.List;

import com.piseth.bank.loan.entity.Loan;

public interface LoanService {
	Loan save(Loan loan);
	
	List<Loan> getList();
	
	//Loan getById(Long loanNumber);
}
