package org.example.flowershopss.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class

User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String role = "USER";
}

//CREATE TABLE users (
//    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
//    username VARCHAR(255) UNIQUE,
//    password VARCHAR(255),
//    email    VARCHAR(255) UNIQUE,
//    role     VARCHAR(255)
//);
