package com.restapi.microservices.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello Payment";
	}
	
	
}
