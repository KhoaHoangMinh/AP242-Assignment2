package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        alertThresholdHigh = highThreshold;
        alertThresholdLow = lowThreshold;
    }

    public Map<String, Double> getLastAlertedPrice() {return lastAlertedPrices;}

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        String stockCode = stockPrice.getCode();
        double newPrice = stockPrice.getAvgPrice();
        alertAbove(stockCode, newPrice);
        alertBelow(stockCode, newPrice);
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
//        Logger.notImplementedYet("alertAbove");
        if(price > alertThresholdHigh) {
            Logger.logAlert(stockCode, price);
            lastAlertedPrices.put(stockCode, price);
        }
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
//        Logger.notImplementedYet("alertBelow");
        if(price < alertThresholdLow) {
            Logger.logAlert(stockCode, price);
            lastAlertedPrices.put(stockCode, price);
        }
    }
}
