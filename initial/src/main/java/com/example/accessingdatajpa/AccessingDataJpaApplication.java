package com.example.accessingdatajpa;

import com.example.accessingdatajpa.entity.Customer;
import com.example.accessingdatajpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AccessingDataJpaApplication {

	// Get logger
	private final static Logger LOGGER = LoggerFactory.getLogger(AccessingDataJpaApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	// CommandLineRunner, runs when application starts!
	@Bean
	public CommandLineRunner run(CustomerRepository repository) {
		return (args) -> {
			// Store new customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("John", "P"));
			repository.save(new Customer("Don", "G"));
			repository.save(new Customer("Jamie", "Oliver"));
			repository.save(new Customer("Matthew", "Gamie"));

			// Fetch all customers
			List<Customer> customers = new ArrayList<>();
			// Add all the customers to the array list
			repository.findAll().forEach(customers::add);
			LOGGER.info("Customers found with findAll():");
			for (Customer c: customers) {
				LOGGER.info("First Name: " + c.getFirstName());
			}

			Customer customer = repository.findById(1L);
			LOGGER.info("Customer found with findById(1L):");
			LOGGER.info("--------------------------------");
			LOGGER.info(customer.toString());
			LOGGER.info("");

		};
	}

}
