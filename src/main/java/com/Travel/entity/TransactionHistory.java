package com.Travel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionHistoryId;

	private int debitAmount;

	private int creditAmount;

	private int closingBalance;

	@ManyToOne
	@JoinColumn(name = "tap_card_id")

	private TapCard tapCard;

//	@OneToOne // Change this annotation
//    private TapCard tapCard;

	@ManyToOne
	@JoinColumn(name = "travel_status_id")
	private TravelStatus travelStatus;

	public int getTransactionHistoryId() {
		return transactionHistoryId;
	}

	public void setTransactionHistoryId(int transactionHistoryId) {
		this.transactionHistoryId = transactionHistoryId;
	}

	public int getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(int debitAmount) {
		this.debitAmount = debitAmount;
	}

	public int getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(int creditAmount) {
		this.creditAmount = creditAmount;
	}

	public int getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(int closingBalance) {
		this.closingBalance = closingBalance;
	}

	public TapCard getTapCard() {
		return tapCard;
	}

	public void setTapCard(TapCard tapCard) {
		this.tapCard = tapCard;
	}

	public TravelStatus getTravelStatus() {
		return travelStatus;
	}

	public void setTravelStatus(TravelStatus travelStatus) {
		this.travelStatus = travelStatus;
	}

}
