package com.piseth.bank.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.bank.loan.dto.LoanDTO;
import com.piseth.bank.loan.entity.Loan;
import com.piseth.bank.loan.mapper.LoanMapper;
import com.piseth.bank.loan.service.LoanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@Autowired
	private LoanMapper loanMapper;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody LoanDTO dto){
		Loan loan = loanService.save(loanMapper.toLoan(dto));
		return ResponseEntity.status(HttpStatus.CREATED).body(loan);
	}
	
	@GetMapping
	public ResponseEntity<?> list(){
		return ResponseEntity.ok(loanService.getList());
	}
	
	@GetMapping("{customerId}")
	public ResponseEntity<?> getByCustomerId(
			@RequestHeader("pisethbank-correlation-id") String correlationId,
			@PathVariable Long customerId){
		//System.out.println("=========== Loan service is called ==============");
		//log.debug("Correlation id found: {}", correlationId);
		
		log.debug("fetchLoanDetail method start");
		List<Loan> loans = loanService.getByCustomerId(customerId);
		log.debug("fetchLoanDetail method end");
		return ResponseEntity.ok(loans);
	}
}
