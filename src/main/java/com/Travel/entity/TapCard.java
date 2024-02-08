 	package com.Travel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TapCard {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "card_generator")
	@SequenceGenerator(
	initialValue = 100000, name = "card_generator")
	private int tabCardId;

	private int tabBalance;
	@Size(min = 3, max = 25, message = "Status should be Min 3 and Max 25 : ")
	private String tabCardStatus;

	@OneToOne
	@JoinColumn(name = "user_info_id")
	@JsonBackReference()
	private UserInfo userInfo;

	@OneToMany(mappedBy = "tapCard", cascade = CascadeType.ALL)

	@JsonIgnore
	private List<TransactionHistory> walletTransactions;

	@OneToMany(mappedBy = "tapCard", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TravelStatus> travelStatusList;

	public int getTabCardId() {
		return tabCardId;
	}

	public void setTabCardId(int tabCardId) {
		this.tabCardId = tabCardId;
	}

	public int getTabBalance() {
		return tabBalance;
	}

	public void setTabBalance(int tabBalance) {
		this.tabBalance = tabBalance;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<TransactionHistory> getWalletTransactions() {
		return walletTransactions;
	}

	public void setWalletTransactions(List<TransactionHistory> walletTransactions) {
		this.walletTransactions = walletTransactions;
	}

	public List<TravelStatus> getTravelStatusList() {
		return travelStatusList;
	}

	public String getStatus() {
		return tabCardStatus;
	}

	public void setStatus(String status) {
		this.tabCardStatus = status;
	}

	public void setTravelStatusList(List<TravelStatus> travelStatusList) {
		this.travelStatusList = travelStatusList;
	}

}
