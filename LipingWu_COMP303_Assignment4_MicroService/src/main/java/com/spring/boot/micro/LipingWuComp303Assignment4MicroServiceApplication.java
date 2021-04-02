package com.spring.boot.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LipingWuComp303Assignment4MicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LipingWuComp303Assignment4MicroServiceApplication.class, args);
		System.out.println("Eureka Server started");
	}

}
