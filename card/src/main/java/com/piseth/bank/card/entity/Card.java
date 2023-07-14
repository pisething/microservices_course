package com.piseth.bank.card.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "cards")
@Data
public class Card {
	private Long cardId;
	private Long customerId;
	private String cardNumber;
	private String cardType;
	private BigDecimal totalLimit;
	private BigDecimal amountUsed;
	private BigDecimal availableAmount;
	private LocalDate createDate;
	
}
