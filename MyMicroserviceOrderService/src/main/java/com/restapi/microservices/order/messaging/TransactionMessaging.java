package com.restapi.microservices.order.messaging;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.restapi.microservices.order.model.Transaction;

@Service
public class TransactionMessaging {

	 @Autowired
	private JmsTemplate jmsTemplate; 
	
	public  void publishTransactionId(Long id) {
		String queueName = "transaction-approval";
		/* publish something in the queue */
		jmsTemplate.convertAndSend(queueName,id );
	}
}
