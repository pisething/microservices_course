package com.piseth.bank.card.service;

import java.util.List;

import com.piseth.bank.card.entity.Card;

public interface CardService {
	Card save(Card loan);
	
	List<Card> getList();
	
	//Loan getById(Long loanNumber);
}
