package com.example.demo.controller;

import com.example.demo.model.Userdetail;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}/balance")
    public Double getBalance(@PathVariable String username) {
        Userdetail user = userRepository.findByUsername(username);
        return user != null ? user.getWalletBalance() : null;
    }
}
