package com.example.cleanitbackend.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanitbackend.Model.Greeting;
import com.example.cleanitbackend.Model.Order;

@CrossOrigin(origins = {"http://localhost:4200", "http://frontend:4200"})
@RestController
public class OrderController {
    private final List<Order> orders = new ArrayList<>();

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/ordersByUserId")
    public ResponseEntity<List<Order>> getOrdersForUserid(@RequestParam String userId) {
        List<Order> userOrders = new ArrayList<>();
        
        for (Order order : orders) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return ResponseEntity.ok(userOrders);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        orders.add(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    // Initialisierung mit ein paar Beispielordern
    public OrderController() {
        orders.add(new Order(1, "user1", new String[]{"item1", "item2"}));
        orders.add(new Order(2, "user2", new String[]{"item3", "item4"}));
        orders.add(new Order(3, "user2", new String[]{"item1", "item2"}));
        orders.add(new Order(4, "user3", new String[]{"item3", "item4"}));
    }

    // Example from https://spring.io/guides/gs/rest-service/
    private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();    

    @GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
