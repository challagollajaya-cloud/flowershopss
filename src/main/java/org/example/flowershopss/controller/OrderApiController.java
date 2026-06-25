package org.example.flowershopss.controller;

import org.example.flowershopss.model.Order;
import org.example.flowershopss.model.User;
import org.example.flowershopss.service.OrderService;
import org.example.flowershopss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000",
        allowCredentials = "true")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // Place order
    @PostMapping("/checkout")
    public Order checkout(Principal principal) {
        User user = userService
                .findByUsername(principal.getName());
        return orderService.placeOrder(user);
    }

    // Get my orders
    @GetMapping
    public List<Order> getOrders(Principal principal) {
        User user = userService
                .findByUsername(principal.getName());
        return orderService.getUserOrders(user);
    }
}
