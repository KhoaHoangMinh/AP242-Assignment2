package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    // Each stock has a list of viewers that want to receive updates
    // When the price changes, notify(StockPrice stockPrice) will look up the stock code and notify all viewers
    private static StockFeeder instance = null;

    public boolean checkRegister(String code, StockViewer stockViewer) {
        boolean stockExists = false;
        boolean viewerNexists = false;

        for(Stock stock : stockList) {
            if(stock.getCode().equals(code)) {
                stockExists = true;
                break;
            }
        }
        List<StockViewer> viewerList = viewers.get(code);
        if(viewerList != null) { viewerNexists = !viewerList.contains(stockViewer); }
        return stockExists && viewerNexists;
    }

    public boolean checkUnregister(String code, StockViewer stockViewer) {
        boolean stockExists = false;
        boolean viewerExists = false;

        for(Stock stock : stockList) {
            if(stock.getCode().equals(code)) {
                stockExists = true;
                break;
            }
        }
        List<StockViewer> viewerList = viewers.get(code);
        if(viewerList != null) { viewerExists = viewerList.contains(stockViewer); }
        return stockExists && viewerExists;
    }

    public Map<String, List<StockViewer>> getViewers() { return viewers; }
    public List<Stock> getStockList() { return stockList; }

    // TODO: Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if(instance == null) { instance = new StockFeeder(); }
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        if(!stockList.contains(stock)) { stockList.add(stock); }
        else {
            for (Stock pStock : stockList) {
                if(Objects.equals(pStock.getCode(), stock.getCode()) &&
                        !Objects.equals(pStock.getName(), stock.getName())) {
                    stockList.remove(pStock);
                    stockList.add(stock);
                }
            }
        }
        viewers.computeIfAbsent(stock.getCode(), k -> new ArrayList<>());
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
        // TODO: test
        if(!checkRegister(code, stockViewer)) {
            Logger.errorRegister(code);
            return;
        }
        viewers.computeIfAbsent(code, k -> new ArrayList<>());
        viewers.get(code).add(stockViewer);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
        if(!checkUnregister(code, stockViewer)) {
            Logger.errorUnregister(code);
            return;
        }
        viewers.get(code).remove(stockViewer);
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
        List<StockViewer> viewerList = viewers.get(stockPrice.getCode());
        if(viewerList != null) {
            for(StockViewer viewer : viewerList) {
                viewer.onUpdate(stockPrice);
            }
        }
    }
}
