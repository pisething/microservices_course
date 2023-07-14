package com.piseth.bank.card.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CardDTO {
	private Long cardId;
	private Long customerId;
	private String cardNumber;
	private String cardType;
	private BigDecimal totalLimit;
	private BigDecimal amountUsed;
	private BigDecimal availableAmount;
	private LocalDate createDate;
}
