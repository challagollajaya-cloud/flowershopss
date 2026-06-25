package org.example.flowershopss.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
//CREATE TABLE cart (
//    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
//    user_id    BIGINT,     ← links to users table
//    product_id BIGINT,     ← links to products table
//    quantity   INT
//);