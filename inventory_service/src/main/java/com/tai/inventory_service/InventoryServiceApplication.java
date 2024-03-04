package com.tai.inventory_service;

import com.tai.inventory_service.model.Inventory;
import com.tai.inventory_service.repository.InventoryRepository;
import com.tai.inventory_service.service.InventoryService;
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
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);

			Inventory inventory_01 = new Inventory();
			inventory_01.setSkuCode("iphone_15");
			inventory_01.setQuantity(0);

			inventoryRepository.save(inventory_01);
			inventoryRepository.save(inventory);
		};
	}

}
