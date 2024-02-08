package com.Travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Travel.entity.TransactionHistory;
import com.Travel.entity.TravelStatus;
import com.Travel.exception.ValidationException;
import com.Travel.service.TransactionHistoryService;
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RestController

public class TransactionHistoryController {

	@Autowired
	private TransactionHistoryService transactionHistoryService;

//	@Autowired
//	private TapCardService tapCardService;

//	@PostMapping(value = "createWallet")
//	public ResponseEntity<WalletTransaction> createWalletTransaction(@Valid @RequestBody WalletTransaction wallet) {
//		//credit - post(add)
//		//debit - through travel
//		int tapCardId = wallet.getTapCard().getTabCardId();
//		int closingBalance = tapCardService.getTapCardBalanceById(tapCardId)+wallet.getCreditAmount()-wallet.getDebitAmount();
//		if(closingBalance<0) {
//			throw new ValidationException("Not enough balance to checkout");
//		}
//		wallet.setClosingBalance(closingBalance);
//		WalletTransaction createWalletTransaction = wallTransactionService.createWalletTransaction(wallet);
//		String tc = tapCardService.updateTapCardBalanceById(wallet.getTapCard().getTabCardId(), closingBalance);
//		
//		wallet.setTapCard(tc);
//		return  new ResponseEntity<WalletTransaction>(createWalletTransaction, HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/allWalletTransaction")
//	public List<WalletTransaction> retrieveAllWalletTransaction() {
//		return wallTransactionRepository.findAll();
//	}
//
//	@GetMapping("/wallet/{id}")
//	public ResponseEntity<WalletTransaction> retrieveWalletTransaction(@PathVariable Integer id) {
//
//		WalletTransaction retrieveWalletTransaction = wallTransactionService.retrieveWalletTransaction(id);
//
//		return new ResponseEntity<WalletTransaction>(retrieveWalletTransaction, HttpStatus.CREATED);
//	}
//}

//	@DeleteMapping("/deleteWalletTransaction/{id}")
//	public void deleteWalletTransaction(@PathVariable Integer id) {
//		wallTransactionRepository.deleteById(id);
//	}
//	
//	@PutMapping("/walletTransaction/{id}")
//	public WalletTransaction updatewalletTransaction(@RequestBody WalletTransaction wallet, @PathVariable Integer id) {
//
//		Optional<WalletTransaction> walletOptional = wallTransactionRepository.findById(id);
//
//		if (walletOptional.isEmpty())			
//
//			wallet.setWalletId(id);			
//
//		return wallTransactionRepository.save(wallet);
//	}
//	
//}

//	@GetMapping("/{id}")
//    public ResponseEntity<WalletTransaction> retrieveWalletTransaction(@PathVariable Long id) {
//        List<WalletTransaction> retrievedWalletTransaction = wallTransactionService.getTransactionHistoryByTapCardId(id);
//
//        if (retrievedWalletTransaction != null) {
//        	return new ResponseEntity<WalletTransaction>(HttpStatus.OK);
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

	@GetMapping("/getTransactionHistory/{id}")
	public TravelStatus getTravelStatus(@PathVariable int id) {
		TravelStatus travelStatus = transactionHistoryService.retrieveTransactionHistory(id);
		if (travelStatus != null) {
			return travelStatus;
		} else {
			throw new ValidationException("Transaction not found");
		}
	}

	
	@GetMapping("/getHistory/{historyId}")
	public TransactionHistory getTrasactionHistory(@PathVariable int historyId) {
		TransactionHistory history  = transactionHistoryService.getTrasactionHistory(historyId);
		if (history!=null) {
			return history;
		}else {
			throw new ValidationException("Transaction not found");
		}
		
	}
}
