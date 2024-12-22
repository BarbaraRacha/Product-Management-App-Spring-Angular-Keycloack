package enset.ma.inventoryapp;

import enset.ma.inventoryapp.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import enset.ma.inventoryapp.repository.ProductRepository;

import java.util.UUID;

@SpringBootApplication
public class InventoryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository){
		return args -> {
			productRepository.save(Product.builder().id("P1").name("Computer").price(5000).quantity(20).build());
			productRepository.save(Product.builder().id("P2").name("SmartPhone").price(3000).quantity(25).build());
			productRepository.save(Product.builder().id("P3").name("Printer").price(5000).quantity(10).build());

        };
	}
}
