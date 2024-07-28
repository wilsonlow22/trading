package com.example.demo.controller;

import com.example.demo.model.TradeHistory;
import com.example.demo.repository.TradeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    //@Autowired
    private TradeHistoryRepository tradeHistoryRepository;

    @Autowired
    public HistoryController(TradeHistoryRepository tradeHistoryRepository) {
        this.tradeHistoryRepository = tradeHistoryRepository;
    }

    @GetMapping("/{userId}")
    public List<TradeHistory> getUserHistory(@PathVariable Long userId) {
        return tradeHistoryRepository.findByUserId(userId);
    }
}
