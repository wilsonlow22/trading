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
    private Double usdt_quantity;
    private Double ethusdt_quantity;
    private Double btcusdt_quantity;
    
    public Userdetail() {
    }

    public Userdetail(String username, Double usdt_quantity, Double ethusdt_quantity, Double btcusdt_quantity) {
        this.username = username;
        this.usdt_quantity = usdt_quantity;
        this.ethusdt_quantity = ethusdt_quantity;
        this.btcusdt_quantity = btcusdt_quantity;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Double getUsdt_quantity() {
        return usdt_quantity;
    }

    public Double getEthusdt_quantity() {
        return ethusdt_quantity;
    }

    public Double getBtcusdt_quantity() {
        return btcusdt_quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsdt_quantity(Double usdt_quantity) {
        this.usdt_quantity = usdt_quantity;
    }

    public void setEthusdt_quantity(Double ethusdt_quantity) {
        this.ethusdt_quantity = ethusdt_quantity;
    }

    public void setBtcusdt_quantity(Double btcusdt_quantity) {
        this.btcusdt_quantity = btcusdt_quantity;
    }
}
