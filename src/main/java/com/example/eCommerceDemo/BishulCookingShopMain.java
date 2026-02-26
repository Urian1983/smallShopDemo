package com.example.eCommerceDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BishulCookingShopMain {

	public static void main(String[] args) {
		SpringApplication.run(BishulCookingShopMain.class, args);
	}

}
