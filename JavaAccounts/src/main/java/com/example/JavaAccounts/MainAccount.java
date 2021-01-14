package com.example.JavaAccounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableEurekaClient
@EnableDiscoveryClient
public class MainAccount {

	public static void main(String[] args) {
		SpringApplication.run(MainAccount.class, args);
	}



}
