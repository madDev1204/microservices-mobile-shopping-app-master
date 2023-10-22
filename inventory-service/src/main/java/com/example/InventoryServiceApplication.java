package com.example;

import com.example.entity.Inventory;
import com.example.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication{
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	//bean is created and run on application startup - quick way to insert data without a POST mapping
	//alternate is using implements CommandLineRunner and overriding run method like done in lab
	//commented after running once...
//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("iphone-13");
//			inventory.setQuantity(100);
//
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("iphone-12");
//			inventory1.setQuantity(0);
//
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//		};
//	}
}

