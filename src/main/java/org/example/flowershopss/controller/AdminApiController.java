package org.example.flowershopss.controller;

import org.example.flowershopss.model.Order;
import org.example.flowershopss.model.Product;
import org.example.flowershopss.service.OrderService;
import org.example.flowershopss.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    // Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Add product
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    // Update product
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {
        product.setId(id);
        productService.saveProduct(product);
        return ResponseEntity.ok("Product updated!");
    }

    // Delete product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted!");
    }

    // Get all orders
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Update order status
    @PutMapping("/orders/{id}/status")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok("Status updated!");
    }
}