package com.restapi.microservices.order.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.microservices.order.dto.TransactionRequestDto;
import com.restapi.microservices.order.enums.TransactionState;
import com.restapi.microservices.order.messaging.TransactionMessaging;
import com.restapi.microservices.order.model.Transaction;
import com.restapi.microservices.order.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private TransactionMessaging messageObj; 
	
	@PostMapping("/process")
	public ResponseEntity<?> processTransaction(@RequestBody TransactionRequestDto dto,
									Transaction transaction) {
		/* Attach dto values to transaction model */
		transaction.setSenderId(dto.getSenderId());
		transaction.setBeneficiaryId(dto.getBeneficiaryId());
		transaction.setAmount(dto.getAmount());
		transaction.setDateOfTransaction(LocalDate.now());
		transaction.setTransactionState(TransactionState.PROCESSED);
		
		/* Save it in DB */
		Transaction t = transactionService.insertTransaction(transaction);
		
		/* Add a message in the Queue giving Transaction ID for approval */
		messageObj.publishTransactionId(t.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body("Transaction processed");
	}
}
