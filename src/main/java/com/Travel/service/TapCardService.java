package com.Travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Travel.entity.TapCard;
import com.Travel.entity.TransactionHistory;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TapCardService {

	public abstract List<TapCard> findAllTapCard();

	public abstract Optional<TapCard> findTapCardById(Integer id);

//	public abstract TapCard createTapCard(TapCard card);

	public ResponseEntity<HttpStatus> deleteTapCardById(Integer id);

	public Optional<TapCard> updateTapCardById(TapCard card, Integer id);

	public abstract String updateTapCardBalanceById(Integer id, int balance)throws JsonProcessingException;

	public abstract int getTapCardBalanceById(Integer id);
	public abstract List<TransactionHistory>getAlltransactionsByCardId(int id);
	
	// New method to get all tap cards
    List<TapCard> getAllTapCards();

}
