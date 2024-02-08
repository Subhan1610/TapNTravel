package com.Travel.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.Travel.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer>{
	
}
