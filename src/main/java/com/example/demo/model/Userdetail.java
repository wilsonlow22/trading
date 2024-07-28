package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Userdetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Double walletBalance;
    
    public Userdetail() {
    }

    public Userdetail(String username, Double walletBalance) {
        this.username = username;
        this.walletBalance = walletBalance;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Double getWalletBalance() {
        return walletBalance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
    }
}
