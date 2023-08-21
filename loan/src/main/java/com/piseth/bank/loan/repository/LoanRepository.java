package com.piseth.bank.loan.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.piseth.bank.loan.entity.Loan;

public interface LoanRepository extends MongoRepository<Loan, Long>{
	List<Loan> findByCustomerId(Long customerId);
}
