package org.example.flowershopss.controller;

import org.example.flowershopss.model.User;
import org.example.flowershopss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private UserService userService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody User user) {
        String result = userService.registerUser(user);
        if (result.equals("success")) {
            return ResponseEntity.ok(
                    "Registered successfully!");
        }
        return ResponseEntity.badRequest().body(result);
    }

    // Check who is logged in
    @GetMapping("/status")
    public ResponseEntity<String> status(
            Principal principal) {
        if (principal != null) {
            return ResponseEntity.ok(
                    principal.getName());
        }
        return ResponseEntity
                .status(401)
                .body("Not logged in");
    }

    // Get current user details
    @GetMapping("/me")
    public ResponseEntity<String> me(
            Principal principal) {
        if (principal != null) {
            return ResponseEntity.ok(
                    principal.getName());
        }
        return ResponseEntity
                .status(401)
                .body("Not logged in");
    }
}
