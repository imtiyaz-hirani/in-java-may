package com.restapi.microservices.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.microservices.payment.TransactionState;
import com.restapi.microservices.payment.model.Transaction;
import com.restapi.microservices.payment.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repo; 
	
	public Transaction fetchTransactionById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	public void updateTransactionState(Transaction transaction, TransactionState approved) {
		transaction.setTransactionState(approved);
		repo.save(transaction);
		
	}

	 

	
}
