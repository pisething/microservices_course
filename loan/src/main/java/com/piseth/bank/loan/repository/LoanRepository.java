package com.piseth.bank.loan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.piseth.bank.loan.entity.Loan;

public interface LoanRepository extends MongoRepository<Loan, Long>{

}
