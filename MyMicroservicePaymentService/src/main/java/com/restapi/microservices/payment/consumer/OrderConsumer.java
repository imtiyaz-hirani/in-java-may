package com.restapi.microservices.payment.consumer;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class OrderConsumer {

	static {
		System.out.println("comsumer called....");
	}
	@JmsListener(destination = "order-queue")
	public void listener(String message) {
		//DB op
		System.out.println("got the message:  " + message);
	}
}
