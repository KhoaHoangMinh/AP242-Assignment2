package com.myproject;

import java.util.ArrayList;
import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TODO: Implement constructor
        this.hoseLib = hoseLib;
        this.stockCodes = stockCodes;
    }

    @Override
    public List<StockPrice> fetch() {
        // TODO: Fetch stock data and convert it to StockPrice list
//        return null;
        List<HoseData> hoseDataLists = hoseLib.getPrices(stockCodes);
        List<StockPrice> stockPriceList = new ArrayList<>();
        for(HoseData data : hoseDataLists) {
            stockPriceList.add(convertToStockPrice(data));
        }
        return stockPriceList;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // TODO: Convert HoseData to StockPrice
//        return null;
        return new StockPrice(hoseData.getStockCode(), hoseData.getPrice(), hoseData.getVolume(), hoseData.getTimestamp());
    }
}
