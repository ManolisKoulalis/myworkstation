package com.customers.customerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CustomerSpringBootAppApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CustomerSpringBootAppApplication.class, args);
	}

}
