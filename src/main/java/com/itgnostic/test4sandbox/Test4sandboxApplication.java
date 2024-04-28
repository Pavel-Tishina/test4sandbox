package com.itgnostic.test4sandbox;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@SpringBootApplication
public class Test4sandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(Test4sandboxApplication.class, args);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setEntityManagerFactoryInterface(EntityManagerFactory.class); // Set to use javax.persistence.EntityManagerFactory
		// Other configurations for your EntityManagerFactory
		return emf;
	}





}
