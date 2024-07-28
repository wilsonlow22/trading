package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private Double bidPrice;
    private Double askPrice;

    public Price() {
    }

    public Price(String symbol, Double bidPrice, Double askPrice) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }    

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }
}
