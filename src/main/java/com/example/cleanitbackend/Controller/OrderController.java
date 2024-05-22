package com.example.cleanitbackend.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cleanitbackend.Dto.OrderDto;
import com.example.cleanitbackend.Model.Order;
import com.example.cleanitbackend.Model.User;

@CrossOrigin(origins = {"http://localhost:4200", "http://frontend:4200"})
@RestController
public class OrderController {
    @Autowired
    private AuthController authController;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final List<Order> orders = new ArrayList<>();
    private AtomicLong orderCounter = new AtomicLong();
    
    public OrderController() {
        LOGGER.info("Creating initial orders");
        orders.add(new Order(orderCounter.incrementAndGet(), 1, "note1", new String[]{"item1", "item2"}, "Leon One"));
        orders.add(new Order(orderCounter.incrementAndGet(), 2, "note2", new String[]{"item3", "item4"}, "Luisa Two"));
        orders.add(new Order(orderCounter.incrementAndGet(), 3, "note3", new String[]{"item1", "item2"}, "Leopold Three"));
        orders.add(new Order(orderCounter.incrementAndGet(), 1, "note4", new String[]{"item5", "item6"}, "Leon One"));
        orders.add(new Order(orderCounter.incrementAndGet(), 1, "note5", new String[]{"item7", "item8"}, "Leon One"));
        orders.add(new Order(orderCounter.incrementAndGet(), 2, "note6", new String[]{"item9", "item10"}, "Luisa Two"));
        orders.add(new Order(orderCounter.incrementAndGet(), 2, "note7", new String[]{"item11", "item12"}, "Luisa Two"));
        orders.add(new Order(orderCounter.incrementAndGet(), 3, "note8", new String[]{"item13", "item14"}, "Leopold Three"));
        orders.add(new Order(orderCounter.incrementAndGet(), 3, "note9", new String[]{"item15", "item16"}, "Leopold Three"));
        orders.add(new Order(orderCounter.incrementAndGet(), 4, "note4", new String[]{"item3", "item4"}, "Customer Four"));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        LOGGER.info("Getting all orders");
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/ordersByUserId")
    public ResponseEntity<List<Order>> getOrdersForUserid(@RequestParam long userId) {
        List<Order> userOrders = new ArrayList<>();
        
        for (Order order : orders) {
            if (order.getUserId() == userId) {
                userOrders.add(order);
            }
        }
        LOGGER.info("Getting orders for user with id: " + userId + " found " + userOrders.size() + " orders.");
        return ResponseEntity.ok(userOrders);
    }

    @GetMapping("/ordersByUserName")
    public ResponseEntity<List<Order>> getOrdersForUserName(@RequestParam String userName) {
        List<Order> userOrders = new ArrayList<>();

        String lowerCaseUserName = userName.toLowerCase();

        for (Order order : orders) {
            String lowerCaseOrderUserName = order.getUserName().toLowerCase();
            
            if (lowerCaseOrderUserName.contains(lowerCaseUserName)) {
                userOrders.add(order);
            }
        }
        
        LOGGER.info("Getting orders for user with name: " + userName + " found " + userOrders.size() + " orders.");
        return ResponseEntity.ok(userOrders);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto OrderDto) {
        Optional<User> user = authController.getUserById(OrderDto.getUserId());
        if (!user.isPresent()) {
            LOGGER.info("User with id: " + OrderDto.getUserId() + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Order order = new Order(orderCounter.incrementAndGet(), OrderDto.getUserId(), OrderDto.getNotes(), OrderDto.getItems(), user.get().getName());
        orders.add(order);
        LOGGER.info("Created new order with id: " + order.getId() + " for user with id: " + order.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
