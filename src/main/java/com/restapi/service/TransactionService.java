package com.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.model.Transaction;
import com.restapi.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	public void insertTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		
	}

}
