package org.example.flowershopss.controller;

import org.example.flowershopss.model.Cart;
import org.example.flowershopss.model.User;
import org.example.flowershopss.service.CartService;
import org.example.flowershopss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000",
        allowCredentials = "true")
public class CartApiController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    // Get cart items
    @GetMapping
    public List<Cart> getCart(Principal principal) {
        User user = userService
                .findByUsername(principal.getName());
        return cartService.getCartByUser(user);
    }

    // Add to cart
    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            Principal principal) {
        User user = userService
                .findByUsername(principal.getName());
        cartService.addToCart(user, productId, quantity);
        return "Added to cart!";
    }

    // Remove from cart
    @DeleteMapping("/{cartId}")
    public String removeFromCart(
            @PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return "Removed!";
    }

    // Get cart total
    @GetMapping("/total")
    public double getTotal(Principal principal) {
        User user = userService
                .findByUsername(principal.getName());
        List<Cart> items = cartService.getCartByUser(user);
        return cartService.getCartTotal(items);
    }
}