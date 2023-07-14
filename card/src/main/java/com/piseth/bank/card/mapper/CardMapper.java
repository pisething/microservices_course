package com.piseth.bank.card.mapper;

import org.mapstruct.Mapper;

import com.piseth.bank.card.dto.CardDTO;
import com.piseth.bank.card.entity.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {
	
	Card toCard(CardDTO dto);
	
}
