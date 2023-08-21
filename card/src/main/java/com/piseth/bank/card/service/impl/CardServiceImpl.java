package com.piseth.bank.card.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piseth.bank.card.entity.Card;
import com.piseth.bank.card.repository.CardRepository;
import com.piseth.bank.card.service.CardService;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	private CardRepository loanRepository;
	@Override
	public Card save(Card loan) {
		return loanRepository.save(loan);
	}
	@Override
	public List<Card> getList() {
		return loanRepository.findAll();
	}
	@Override
	public List<Card> getByCustomerId(Long customerId) {
		return loanRepository.findByCustomerId(customerId);
	}

}
