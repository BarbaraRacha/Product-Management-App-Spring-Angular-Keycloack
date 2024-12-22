package enset.ma.orderservice.web;

import enset.ma.orderservice.entities.Order;
import enset.ma.orderservice.repositories.OrderRepository;
import enset.ma.orderservice.restClients.InventoryRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryRestClient inventoryRestClient;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        allOrders.forEach(o -> {
            o.getProductItems().forEach(pi -> {
                pi.setProduct(inventoryRestClient.getProductById(pi.getProductId()));
            });
        });
        return allOrders;
    }

    @GetMapping("/orders/{id}")
    public Order getAllOrders(@PathVariable String id) {
        Order order = orderRepository.findById(id).get();
        order.getProductItems().forEach(pi->{
            pi.setProduct(inventoryRestClient.getProductById(pi.getProductId()));
        });
        return order;
    }
}
