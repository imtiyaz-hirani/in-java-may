package com.restapi.microservices.payment.consumer;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.restapi.microservices.payment.TransactionState;
import com.restapi.microservices.payment.model.Transaction;
import com.restapi.microservices.payment.service.TransactionService;

@Component
@EnableJms
public class TransactionStatusUpdate {
	
	@Autowired
	private TransactionService service; 
	
	@JmsListener(destination = "transaction-approval")
	public void listener(Long id) {
		Scanner sc = new Scanner(System.in);
		//DB op
		Transaction transaction = service.fetchTransactionById(id);
		
		System.out.println("********TRANSACTION***********");
		System.out.println("Sender ID: " + transaction.getSenderId());
		System.out.println("Beneficiary ID: " + transaction.getBeneficiaryId());
		System.out.println("Amount to be transfered: " + transaction.getAmount());
		System.out.println("Processing Date: " + transaction.getDateOfTransaction());
		System.out.println("******************************");
		System.out.println("Do you wish to approve this transaction?");
		System.out.println(" Press 1.YES  |  2.NO");
		int resp = sc.nextInt();
		System.out.println("Verify Urself, Enter password");
		String password = sc.next();
		
		if(resp == 1 && password.equals("1234")) {
			service.updateTransactionState(transaction,TransactionState.APPROVED);
			System.out.println("Transaction Approved!!!");
		}
		else {
			service.updateTransactionState(transaction,TransactionState.DENIED);
			System.out.println("Transaction Denied!!!");
		}
		 
	}
}
