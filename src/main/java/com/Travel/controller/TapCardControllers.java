package com.Travel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Travel.entity.TapCard;
import com.Travel.entity.TransactionHistory;
import com.Travel.exception.ValidationException;

import com.Travel.service.TapCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RestController
public class TapCardControllers {

	@Autowired
	private TapCardService tapCardService;

//	@PostMapping(value = "/TapCard")
//	public ResponseEntity<TapCard> createTapCard(@Valid @RequestBody TapCard tapCard) {
//
//		TapCard createTapCard = tapCardService.createTapCard(tapCard);
//		return new ResponseEntity<TapCard>(createTapCard, HttpStatus.CREATED);

	@GetMapping("/tapcards/{id}")
	public ResponseEntity<TapCard> retrieveTapCard(@PathVariable Integer id) {
		Optional<TapCard> card = tapCardService.findTapCardById(id);

		if (card.isEmpty())
			throw new ValidationException("Unable to fetch the TapCard id-" + id);
		return new ResponseEntity<TapCard>(card.get(), HttpStatus.OK);
	}

	@PutMapping("/tapcard/{id}/{amount}")
	public ResponseEntity<String> updateTapCard(@PathVariable Integer id, @PathVariable int amount) throws JsonProcessingException {

		String updateCard = tapCardService.updateTapCardBalanceById(id, amount);

		return new ResponseEntity<String>(updateCard, HttpStatus.OK);
	}

	@GetMapping("/transactions/{id}")
	public ResponseEntity<List<TransactionHistory>> retrieveTransactionHistryByCardId(@PathVariable Integer id) {

		return new ResponseEntity<>(tapCardService.getAlltransactionsByCardId(id), HttpStatus.OK);
	}
	
	
	
	// New endpoint to get all tap cards
    @GetMapping("/getAllTapCards")
    public ResponseEntity<List<TapCard>> getAllTapCards() {
        List<TapCard> tapCards = tapCardService.getAllTapCards();
        return new ResponseEntity<>(tapCards, HttpStatus.OK);
    }

}
