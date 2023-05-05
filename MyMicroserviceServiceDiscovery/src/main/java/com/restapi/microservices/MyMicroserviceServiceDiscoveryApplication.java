package com.restapi.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MyMicroserviceServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMicroserviceServiceDiscoveryApplication.class, args);
	}

}
