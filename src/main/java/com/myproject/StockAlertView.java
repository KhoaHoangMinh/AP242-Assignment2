package com.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock
    private Map<String, Double> lastPrices = new HashMap<>();

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        alertThresholdHigh = highThreshold;
        alertThresholdLow = lowThreshold;
    }

    public Map<String, Double> getLastAlertedPrice() { return lastAlertedPrices; }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        String code = stockPrice.getCode();
        lastPrices.computeIfAbsent(code, k -> stockPrice.getAvgPrice());
        if(lastAlertedPrices.get(stockPrice.getCode()) == null ||
                stockPrice.getAvgPrice() != lastPrices.get(code)) {
            alertAbove(code, stockPrice.getAvgPrice());
            alertBelow(code, stockPrice.getAvgPrice());
            lastPrices.put(code, stockPrice.getAvgPrice());
        }
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        if(price >= alertThresholdHigh) {
            Logger.logAlert(stockCode, price);
            lastAlertedPrices.put(stockCode, price);
        }
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        if(price <= alertThresholdLow) {
            Logger.logAlert(stockCode, price);
            lastAlertedPrices.put(stockCode, price);
        }
    }
}
