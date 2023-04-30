package com.eyamattoussi.inventoryservice;

import com.eyamattoussi.inventoryservice.model.Inventory;
import com.eyamattoussi.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialData(InventoryRepository inventoryRepository){
		return args -> {
			inventoryRepository.save(new Inventory(1L, "iphone_13", 100));
			inventoryRepository.save(new Inventory(2L, "iphone_13_red", 0));
		};
	}
}