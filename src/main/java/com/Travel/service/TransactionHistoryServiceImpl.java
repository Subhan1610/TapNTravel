package com.Travel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Travel.entity.TransactionHistory;
import com.Travel.entity.TravelStatus;
import com.Travel.exception.ValidationException;
import com.Travel.repository.TransactionHistoryRepository;
import com.Travel.repository.TravelStausRepository;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;
	@Autowired
	private TravelStausRepository travelStausRepository;


	@Override
	public TravelStatus retrieveTransactionHistory(int id) {

		Optional<TransactionHistory> history = transactionHistoryRepository.findById(id);

		if (history.isPresent()) {
			TransactionHistory transactionHistory = history.get();
			TravelStatus travelStatus = transactionHistory.getTravelStatus();
			return travelStatus;
		} else {
			throw new ValidationException("Transaction not found for ID: \" + id");
		}

	}


	@Override
	public TransactionHistory getTrasactionHistory(int historyId) {
	Optional<TransactionHistory> optional =	transactionHistoryRepository.findById(historyId);
	if (optional.isPresent()) {
		TransactionHistory history = optional.get();
	
	return history;
		
	}else {
		throw new ValidationException("Transaction not found for ID: \" + id");
	}
	}
}

//	@Override
//	public TransactionHistory createTransactionHistory(@Valid TransactionHistory wallet) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Autowired
//	private WalletTransactionRepository walletTransactionRepository;
//
//	@Override
//	public WalletTransaction createWalletTransaction(@Valid WalletTransaction wallet) {		
//		return walletTransactionRepository.save(wallet);
//	}
//
//	@Override
//	public WalletTransaction retrieveWalletTransaction(Integer id) {
//	
//		Optional<WalletTransaction> wallet = walletTransactionRepository.findById(id);
//
//		if (wallet.isEmpty())
//			throw new ValidationException("id-" + id);
//
//		return wallet.get();
//		
//	}


