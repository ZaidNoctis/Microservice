package com.microserver.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableRabbit
public class MicroserverLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserverLoginApplication.class, args);
	}

}
