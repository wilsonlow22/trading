package com.example.demo.controller;

import com.example.demo.model.Price;
import com.example.demo.model.TradeHistory;
import com.example.demo.model.Userdetail;
import com.example.demo.repository.PriceRepository;
import com.example.demo.repository.TradeHistoryRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeHistoryRepository tradeHistoryRepository;

    @PostMapping("/buy")
    public String buy(@RequestParam String username, @RequestParam String symbol, @RequestParam Double amount) {
        Userdetail user = userRepository.findByUsername(username);
        Price price = priceRepository.findBySymbol(symbol);

        if (user == null || price == null) {
            return "User or Price not found";
        }

        double cost = amount * price.getAskPrice();
        if (user.getWalletBalance() < cost) {
            return "Insufficient funds";
        }
        
        // add TradeHistory
        TradeHistory trade = new TradeHistory();
        trade.setUserId(user.getId());
        trade.setSymbol(symbol);
        trade.setTradeType("BUY");
        trade.setTradeAmount(amount);
        trade.setTradeTime(LocalDateTime.now());
        tradeHistoryRepository.save(trade);
        
        // update user wallet balance
       user.setWalletBalance(user.getWalletBalance() - cost);
       userRepository.save(user);

        return "Trade successful";
    }

    @PostMapping("/sell")
    public String sell(@RequestParam String username, @RequestParam String symbol, @RequestParam Double amount) {
        Userdetail user = userRepository.findByUsername(username);
        Price price = priceRepository.findBySymbol(symbol);

        if (user == null || price == null) {
            return "User or Price not found";
        }

        // add TradeHistory
        TradeHistory trade = new TradeHistory();
        trade.setUserId(user.getId());
        trade.setSymbol(symbol);
        trade.setTradeType("SELL");
        trade.setTradeAmount(amount);
        trade.setTradeTime(LocalDateTime.now());
        tradeHistoryRepository.save(trade);

        // update user wallet balance
        double cost = amount * price.getBidPrice();
        user.setWalletBalance(user.getWalletBalance() + cost);
        userRepository.save(user);

        return "Trade successful";
    }
}

