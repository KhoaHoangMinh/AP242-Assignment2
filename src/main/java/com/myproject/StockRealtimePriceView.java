package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();
    private Double backupPrice;

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement logic to check if price has changed and log it
        String stockCode = stockPrice.getCode();
        double newPrice = stockPrice.getAvgPrice();
        Double lastPrice = lastPrices.get(stockCode);

        if(lastPrice == null) {
//            Logger.logRealtime(stockCode, newPrice);
            lastPrices.put(stockCode, newPrice);
            backupPrice = newPrice;
        } else if(newPrice != backupPrice) {
            Logger.logRealtime(stockCode, newPrice);
            lastPrices.put(stockCode, backupPrice);
            backupPrice = newPrice;
        }
    }
    public Map<String, Double> getLastPrices() {
        return lastPrices;
    }
}
