package com.piseth.bank.card.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.piseth.bank.card.entity.Card;

public interface CardRepository extends MongoRepository<Card, Long>{

}
