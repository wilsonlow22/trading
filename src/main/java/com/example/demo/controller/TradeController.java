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

        if (user == null) {
            return "User not found";
        }

        if (price == null) {
            return "Price not found";
        }

        double cost = amount * price.getAskPrice();
        if (user.getUsdt_quantity() < cost) {
            return "Insufficient USTD";
        }
        
        // update user's ethusdt quantity or btcusdt quantity based on symbol
        if (symbol.toLowerCase().equals("ethusdt") || symbol.toLowerCase().equals("eth")) {
            user.setEthusdt_quantity(user.getEthusdt_quantity() + amount);
        } else if (symbol.toLowerCase().equals("btcusdt") || symbol.toLowerCase().equals("btc")) {
            user.setBtcusdt_quantity(user.getBtcusdt_quantity() + amount);
        } else {
            return "Invalid symbol";
        }
        
        // update user's USDT quantity
       user.setUsdt_quantity(user.getUsdt_quantity() - cost);
       userRepository.save(user);

        // add TradeHistory
        TradeHistory trade = new TradeHistory();
        trade.setUserId(user.getId());
        trade.setSymbol(symbol);
        trade.setTradeType("BUY");
        trade.setTradeAmount(amount);
        trade.setTradeTime(LocalDateTime.now());
        tradeHistoryRepository.save(trade);
        
        // return success message with the trade details of balance
        String message = "Buy Trade successful\n";
        message += "Buy: " + amount + " " + symbol + "\n";
        message += "Total Cost: " + cost + "\n";
        message += "USDT Balance: " + user.getUsdt_quantity() + "\n";
        message += "ETH Balance: " + user.getEthusdt_quantity() + "\n";
        message += "BTC Balance: " + user.getBtcusdt_quantity() + "\n";

        return message;        
    }

    @PostMapping("/sell")
    public String sell(@RequestParam String username, @RequestParam String symbol, @RequestParam Double amount) {
        Userdetail user = userRepository.findByUsername(username);
        Price price = priceRepository.findBySymbol(symbol);

        if (user == null) {
            return "User not found";
        }

        if (price == null) {
            return "Price not found";
        }

        // check user's ethusdt quantity or btcusdt quantity based on symbol
        if (symbol.toLowerCase().equals("ethusdt") || symbol.toLowerCase().equals("eth")) {
            if (user.getEthusdt_quantity() < amount) {
                return "Insufficient ETH";
            }
            user.setEthusdt_quantity(user.getEthusdt_quantity() - amount);
        } else if (symbol.toLowerCase().equals("btcusdt") || symbol.toLowerCase().equals("btc")) {
            if (user.getBtcusdt_quantity() < amount) {
                return "Insufficient BTC";
            }
            user.setBtcusdt_quantity(user.getBtcusdt_quantity() - amount);
        } else {
            return "Invalid symbol";
        }

        // update user USDT quantity
        double totalBidPrice = amount * price.getBidPrice();
        user.setUsdt_quantity(user.getUsdt_quantity() + totalBidPrice);
        userRepository.save(user);

        // add TradeHistory
        TradeHistory trade = new TradeHistory();
        trade.setUserId(user.getId());
        trade.setSymbol(symbol);
        trade.setTradeType("SELL");
        trade.setTradeAmount(amount);
        trade.setTradeTime(LocalDateTime.now());
        tradeHistoryRepository.save(trade);

        // return success message with the trade details of balance
        String message = "Sell Trade successful\n";
        message += "Sell: " + amount + " " + symbol + "\n";
        message += "Total Bid Price: " + totalBidPrice + "\n";
        message += "USDT Balance: " + user.getUsdt_quantity() + "\n";
        message += "ETH Balance: " + user.getEthusdt_quantity() + "\n";
        message += "BTC Balance: " + user.getBtcusdt_quantity() + "\n";

        return message;
    }

    // new method to allow buy or sell (eth or btc) with usdt
    @PostMapping("/trade")
    public String trade(@RequestParam String username, @RequestParam String symbol, @RequestParam Double amount, @RequestParam String tradeType) {
        if (tradeType.toLowerCase().equals("buy")) {
            return buy(username, symbol, amount);
        } else if (tradeType.toLowerCase().equals("sell")) {
            return sell(username, symbol, amount);
        } else {
            return "Invalid trade type";
        }
    }
}

