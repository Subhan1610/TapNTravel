package com.Travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Travel.entity.TapCard;
import com.Travel.entity.TransactionHistory;
import com.Travel.exception.ValidationException;
import com.Travel.repository.TapCardRepository;
import com.Travel.repository.UserInfoRepository;
import com.Travel.service.TravelStatusServiceImpl.MessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Travel.repository.TransactionHistoryRepository;

@Service
public class TapCardServiceImpl implements TapCardService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private TapCardRepository tapCardRepository;
	
	@Autowired
	private TransactionHistoryRepository walletTransactionRepository;

	@Override
	public List<TapCard> findAllTapCard() {

		return tapCardRepository.findAll();
	}

	@Override
	public Optional<TapCard> findTapCardById(Integer id) {

		return tapCardRepository.findById(id);
	}

//	@Override
//	public TapCard createTapCard(TapCard card) {
//		
//		return tapCardRepository.save(card);
//	}

	@Override
	public ResponseEntity<HttpStatus> deleteTapCardById(Integer id) {

		tapCardRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public Optional<TapCard> updateTapCardById(TapCard card, Integer id) {

		Optional<TapCard> updateTapCardById = ((TapCardServiceImpl) tapCardRepository).updateTapCardById(card, id);

		if (updateTapCardById.isEmpty())
			card.setTabCardId(id);

		return Optional.empty();
	}

//	@Override
//	public TapCard updateTapCardBalanceById(Integer id, int balance) {
//		Optional<TapCard> tapCard = tapCardRepository.findById(id);
//
//		System.out.println(tapCard.get().getTabCardId());
//		if (tapCard.isPresent()) {
//			tapCard.get().setTabBalance(tapCard.get().getTabBalance()+balance);
//		}
//		return tapCardRepository.save(tapCard.get());
//	}

	@Override
	public String updateTapCardBalanceById(Integer id, int creditAmount)throws JsonProcessingException {
		Optional<TapCard> tapCardOptional = tapCardRepository.findById(id);
		if (tapCardOptional.isPresent()) {
			TapCard tapCard = tapCardOptional.get();
			int currentBalance = tapCard.getTabBalance();
			int newBalance = currentBalance + creditAmount;
			tapCard.setTabBalance(newBalance);
			tapCardRepository.save(tapCard);
			
			// Calculate closing balance
	        int closingBalance = newBalance;
			
			//WalletTransaction 
			
			TransactionHistory walletTransaction = new TransactionHistory();
			
			walletTransaction.setTapCard(tapCard);
			
			walletTransaction.setCreditAmount(creditAmount);
			
			walletTransaction.setClosingBalance(closingBalance);
			
			walletTransactionRepository.save(walletTransaction);
			String response = "Credited ₹ "+creditAmount+" Closing Balance ₹ "+closingBalance;
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(new MessageResponse(response));
			return jsonString;
		}
		else {
			throw new ValidationException("Invalid TapCard Id");
		}

	}

	@Override
	public int getTapCardBalanceById(Integer id) {
		Optional<TapCard> tapCard = tapCardRepository.findById(id);
		if (!tapCard.isPresent()) {
			throw new ValidationException("TapCard Not Found...");
		}
		return tapCard.get().getTabBalance();
	}

	@Override
	public List<TransactionHistory> getAlltransactionsByCardId(int id) {
		Optional<TapCard>cardOptional= tapCardRepository.findById(id);
		if(cardOptional.isPresent()) {
			return cardOptional.get().getWalletTransactions();
		}
		else {
			throw new ValidationException("Tap card Id not found");
		}
		
	}

	@Override
	public List<TapCard> getAllTapCards() {
		return tapCardRepository.findAll();
	}

}
