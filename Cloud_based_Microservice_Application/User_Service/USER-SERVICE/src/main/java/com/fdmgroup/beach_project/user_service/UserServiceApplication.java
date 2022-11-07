package com.fdmgroup.beach_project.user_service;

import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

    // RestTemplate - Spring web class - consumes RESTful webservice
	@Bean
	@LoadBalanced // balance HTTP requests this service sends to an internal microservice.
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
