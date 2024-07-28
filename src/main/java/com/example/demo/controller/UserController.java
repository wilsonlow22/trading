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

    // display all users
    @GetMapping("")
    public Iterable<Userdetail> getAllUsers() {
        return userRepository.findAll();
    }

    // get user's wallet balance of all quantity of USDT, ETH, BTC
    @GetMapping("/{username}")
    public Userdetail getUser(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
    
}
