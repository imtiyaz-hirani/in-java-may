package com.restapi.microservices.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.microservices.order.TransactionRepository;
import com.restapi.microservices.order.model.Transaction;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction insertTransaction(Transaction transaction) {
	    return transactionRepository.save(transaction);
	}
}
 