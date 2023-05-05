package com.restapi.microservices.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyMicroserviceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMicroserviceOrderServiceApplication.class, args);
	}

}
