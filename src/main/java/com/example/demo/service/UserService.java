package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Userdetail;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUserIfNotExists(String username, Double usdtQuantity) {
        // Check if the user already exists
        Userdetail existingUser = userRepository.findByUsername(username);
        
        // If the user does not exist, insert a new record
        if (existingUser == null) {
            Userdetail newUser = new Userdetail();
            newUser.setUsername(username);
            newUser.setUsdt_quantity(usdtQuantity);
            newUser.setEthusdt_quantity(0.0);
            newUser.setBtcusdt_quantity(0.0);
            userRepository.save(newUser);
        }
    }
}

