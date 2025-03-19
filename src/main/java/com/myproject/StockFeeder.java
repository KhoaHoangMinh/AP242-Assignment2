package com.myproject;

import jdk.jpackage.internal.Log;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    // Each stock has a list of viewers that want to receive updates
    // When the price changes, notify(StockPrice stockPrice) will look up the stock code and notify all viewers
    private static StockFeeder instance = null;

    public boolean checkRegister(String code, StockViewer stockViewer) {
        boolean stockExists = false;
        boolean viewerExists = false;

        for(Stock stock : stockList) {
            if(stock.getCode().equals(code)) {
                stockExists = true;
                break;
            }
        }
        List<StockViewer> viewerList = viewers.get(code);
        if(viewerList != null) {viewerExists = viewerList.contains(stockViewer);}
        return stockExists && viewerExists;
    }

    // TODO: Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if(instance == null) {instance = new StockFeeder();}
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        stockList.add(stock);
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
    }
}
