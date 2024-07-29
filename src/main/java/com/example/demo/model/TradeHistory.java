package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String username;
    private String symbol;
    private Double tradeAmount;
    private String tradeType;
    private LocalDateTime tradeTime;

    public TradeHistory() {
    }

    public TradeHistory(Long userId, String username, String symbol, Double tradeAmount, LocalDateTime tradeTime) {
        this.userId = userId;
        this.username = username;
        this.symbol = symbol;
        this.tradeAmount = tradeAmount;
        this.tradeTime = tradeTime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return username;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getTradeAmount() {
        return tradeAmount;
    }

    public String getTradeType() {
        return tradeType;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTradeAmount(Double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }

}
