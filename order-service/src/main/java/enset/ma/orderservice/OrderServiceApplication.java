package enset.ma.orderservice;

import enset.ma.orderservice.entities.Order;
import enset.ma.orderservice.entities.ProductItem;
import enset.ma.orderservice.enums.OrderState;
import enset.ma.orderservice.model.Product;
import enset.ma.orderservice.repositories.OrderRepository;
import enset.ma.orderservice.repositories.ProductItemRepository;
import enset.ma.orderservice.restClients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(OrderRepository orderRepository,
										ProductItemRepository productItemRepository,
										InventoryRestClient inventoryRestClient){
		return args -> {
			// List<Product> allProducts = inventoryRestClient.getProducts();
			List<String> productsIds = List.of("P1", "P2", "P3");
			for (int i = 0; i <5 ; i++) {
				Order order  = Order.builder()
						.orderId(UUID.randomUUID().toString())
						.date(LocalDate.now())
						.state(OrderState.PENDING)
						.build();
				Order savedOrder = orderRepository.save(order);

				productsIds.forEach(pId ->{
					ProductItem productItem = ProductItem.builder()
							.productId(pId)
							.quantity(new Random().nextInt(20))
							.price(Math.random() * 1000 + 1000)
							.order(savedOrder)
							.build();
					productItemRepository.save(productItem);
				});

				/*allProducts.forEach(product ->{
					ProductItem = ProductItem.builder()
							.productId(product.getId())
							.quantity(new Random().nextInt(20))
							.price(product.getPrice())
							.order(savedOrder)
							.build();
					productItemRepository.save(productItem);
				});*/


			}
        };
	}
}
