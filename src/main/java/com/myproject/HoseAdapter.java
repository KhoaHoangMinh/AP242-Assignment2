package com.myproject;

import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TODO: Implement constructor
    }

    @Override
    public List<StockPrice> fetch() {
        // TODO: Fetch stock data and convert it to StockPrice list
        return null;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // TODO: Convert HoseData to StockPrice
        return null;
    }
}
