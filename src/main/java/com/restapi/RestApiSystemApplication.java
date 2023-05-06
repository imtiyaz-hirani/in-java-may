package com.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RestApiSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiSystemApplication.class, args);
	}

}
