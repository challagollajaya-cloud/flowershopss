package org.example.flowershopss.service;

import org.example.flowershopss.model.*;
import org.example.flowershopss.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    // Place order
    @Transactional
    public Order placeOrder(User user) {
        List<Cart> cartItems =
                cartService.getCartByUser(user);

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(
                cartService.getCartTotal(cartItems));
        order.setStatus("PENDING");

        List<OrderItem> orderItems = new ArrayList<>();
        for (Cart cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getProduct().getPrice());
            orderItems.add(item);
        }

        order.setItems(orderItems);
        orderRepository.save(order);
        cartService.clearCart(user);

        return order;
    }

    // Get user orders
    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUser(user);
    }

    // Get all orders (admin)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Update order status (admin) ← NEW!
    public void updateOrderStatus(
            Long orderId, String status) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found: " + orderId));
        order.setStatus(status);
        orderRepository.save(order);
    }
}