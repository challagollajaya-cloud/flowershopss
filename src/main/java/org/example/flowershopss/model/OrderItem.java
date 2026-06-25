package org.example.flowershopss.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;
}

//CREATE TABLE order_items (
//    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
//    order_id   BIGINT,
//    product_id BIGINT,
//    quantity   INT,
//    price      DOUBLE
//);