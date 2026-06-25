package org.example.flowershopss.service;

import org.example.flowershopss.model.Cart;
import org.example.flowershopss.model.Product;
import org.example.flowershopss.model.User;
import org.example.flowershopss.repository.CartRepository;
import org.example.flowershopss.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Cart> getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public void addToCart(User user, Long productId, int quantity) {
        Optional<Cart> existingCart =
                cartRepository.findByUserAndProductId(user, productId);

        if(existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            cartRepository.save(cart);
        } else {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(productRepository.findById(productId).get());
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Transactional  // ← ADD THIS
    public void clearCart(User user) {
        cartRepository.deleteByUser(user);
    }

    public Double getCartTotal(List<Cart> cartItems) {
        return cartItems.stream()
                .mapToDouble(item ->
                        item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}