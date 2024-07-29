package com.example.demo.controller;

import com.example.demo.model.Price;
import com.example.demo.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    public PriceController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    // new mapping to get all prices list
    @GetMapping("")
    public Iterable<Price> getPrices() {
        return priceRepository.findAll();
    }


    @GetMapping("/{symbol}")
    public Price getPrice(@PathVariable String symbol) {
        return priceRepository.findBySymbol(symbol);
    }
}
