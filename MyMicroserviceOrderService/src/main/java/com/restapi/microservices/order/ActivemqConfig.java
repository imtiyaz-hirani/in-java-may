package com.restapi.microservices.order;

 
 
 import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivemqConfig {

	static {
		System.out.println("active config class loaded...");
	}
	   
	@Bean
	public Queue getQueue() {
		return new ActiveMQQueue("order-queue");
	}
}
/* 
 	PasswordEncoder encoder = new BcryptPasswordEncoder(); 
 	Queue queue = new ActiveMQ();
 */