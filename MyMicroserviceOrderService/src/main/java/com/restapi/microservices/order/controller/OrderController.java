package com.restapi.microservices.order.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private Queue queue;
	@Autowired
	private JmsTemplate jmsTemplate; 
	
	@GetMapping("/hello")
	public String sayHello() {
		/* publish something in the queue */
		jmsTemplate.convertAndSend(queue,"message from order service ID: B132" );
		return "Hello Order";
	}
}
