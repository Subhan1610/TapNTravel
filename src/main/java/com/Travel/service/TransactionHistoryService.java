package com.Travel.service;

import com.Travel.entity.TransactionHistory;
import com.Travel.entity.TravelStatus;

public interface TransactionHistoryService {

//	public TransactionHistory createTransactionHistory(@Valid @RequestBody TransactionHistory wallet);

	public TravelStatus retrieveTransactionHistory(int id);
	
	public TransactionHistory getTrasactionHistory(int historyId);
}
