package com.example.JavaGymExercises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableEurekaClient
@EnableDiscoveryClient
public class MainGym {


	public static void main(String[] args) {
		SpringApplication.run(MainGym.class, args);
	}

}
