package com.example.demo.service;

import com.example.demo.model.Price;
import com.example.demo.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PricingService {

    @Autowired
    private PriceRepository priceRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PricingService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    // private static final String HUOBI_API_URL = "https://api.huobi.pro/market/tickers";

    @Scheduled(fixedRate = 10000) // 10 seconds
    public void fetchAndStorePrices() {
        // RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.huobi.pro/market/tickers";
        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            if (jsonNode.has("data")) {
                JsonNode dataArray = jsonNode.get("data");
                for (JsonNode node : dataArray) {
                    String symbol = node.get("symbol").asText();

                    // Extract the bid and ask prices if symbol = "ethusdt" or "btcusdt", otherwise skip
                    if (!symbol.toLowerCase().equals("ethusdt") && !symbol.toLowerCase().equals("btcusdt")) {
                        continue;
                    }

                    Double bidPrice = node.get("bid").doubleValue();
                    Double askPrice = node.get("ask").doubleValue();

                    // Save or update the price in the database
                    Price existingPrice = priceRepository.findBySymbol(symbol);
                    if (existingPrice != null) {
                        existingPrice.setBidPrice(bidPrice);
                        existingPrice.setAskPrice(askPrice);
                    } else {
                        existingPrice = new Price();
                        existingPrice.setSymbol(symbol);
                        existingPrice.setBidPrice(bidPrice);
                        existingPrice.setAskPrice(askPrice);
                    }
                    priceRepository.save(existingPrice);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}

