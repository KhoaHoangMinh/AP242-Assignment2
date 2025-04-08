/*
 * LTNC - HK242
 * file : Main.java
 * SRC template
 * Author: Võ Tiến
 * Link FB : https://www.facebook.com/Shiba.Vo.Tien
 * Link Group : https://www.facebook.com/groups/khmt.ktmt.cse.bku
 * Date: 22.03.2025
 */
package com.myproject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main1 {
    private static int count_pass_test = 0;
    private static int num_test = 5;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        Hose_Adapter_Test01();
        Hose_Adapter_Test02();
        Hose_Adapter_Test03();
        Hose_Adapter_Test04();
        Hose_Adapter_Test05();
        printResult();
    }
    
    private static void Hose_Adapter_Test01() {
        String filePath = "src/resources/stocks.json";

        HosePriceFetchLib hoseLib = new HosePriceFetchLib(filePath);
        List<String> hoseStockCodes = Arrays.asList("VIC", "VNM", "HPG", "FPT");
        HoseAdapter hoseAdapter = new HoseAdapter(hoseLib, hoseStockCodes);
        
        List<StockPrice> hosePrices = hoseAdapter.fetch();
        for (StockPrice price : hosePrices) {
            System.out.println(price);
        }

        List<StockPrice> actualPrices = hoseAdapter.fetch();

        List<String> RealhoseStockCodes = Arrays.asList("VIC", "VNM", "HPG");
        if (containsAllStockCodes(actualPrices, RealhoseStockCodes)) {
            System.out.println(ANSI_GREEN + "Pass HoseAdapter test 01" + ANSI_RESET);
            count_pass_test++;
        } else {
            System.out.println(ANSI_RED + "Fail HoseAdapter test 01" + ANSI_RESET);
        }
    }

    private static void Hose_Adapter_Test02() {
        String filePath = "src/resources/stocks.json";

        HosePriceFetchLib hoseLib = new HosePriceFetchLib(filePath);
        List<String> hoseStockCodes = Arrays.asList("VIC", "VNM", "HPG", "VCB", "MWG");
        HoseAdapter hoseAdapter = new HoseAdapter(hoseLib, hoseStockCodes);

        List<StockPrice> actualPrices = hoseAdapter.fetch();
        System.out.println("Actual prices:");
        for (StockPrice price : actualPrices) {
            System.out.println(price);
        }

        if (containsAllStockCodes(actualPrices, hoseStockCodes)) {
            System.out.println(ANSI_GREEN + "Pass HoseAdapter test 02" + ANSI_RESET);
            count_pass_test++;
        } else {
            System.out.println(ANSI_RED + "Fail HoseAdapter test 02" + ANSI_RESET);
        }
    }

    private static void Hose_Adapter_Test03() {
        String filePath = "src/resources/stocks.json";

        HosePriceFetchLib hoseLib = new HosePriceFetchLib(filePath);
        List<String> hoseStockCodes = Arrays.asList("VIC");
        HoseAdapter hoseAdapter = new HoseAdapter(hoseLib, hoseStockCodes);

        List<StockPrice> actualPrices = hoseAdapter.fetch();
        System.out.println("Actual prices:");
        for (StockPrice price : actualPrices) {
            System.out.println(price);
        }

        if (containsAllStockCodes(actualPrices, hoseStockCodes)) {
            System.out.println(ANSI_GREEN + "Pass HoseAdapter test 03" + ANSI_RESET);
            count_pass_test++;
        } else {
            System.out.println(ANSI_RED + "Fail HoseAdapter test 03" + ANSI_RESET);
        }
    }

    private static void Hose_Adapter_Test04() {
        String filePath = "src/resources/stocks.json";

        HosePriceFetchLib hoseLib = new HosePriceFetchLib(filePath);
        List<String> hoseStockCodes = Arrays.asList("PWM");
        HoseAdapter hoseAdapter = new HoseAdapter(hoseLib, hoseStockCodes);

        List<StockPrice> actualPrices = hoseAdapter.fetch();
        System.out.println("No data for PWM");
        for (StockPrice price : actualPrices) {
            System.out.println(price);
        }
        if (!actualPrices.isEmpty()) {
            System.out.println(ANSI_RED + "Fail HoseAdapter test 04" + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "Pass HoseAdapter test 04" + ANSI_RESET);
            count_pass_test++;
        }
    }

    private static void Hose_Adapter_Test05() {
        String filePath = "src/resources/stocks.json";

        HosePriceFetchLib hoseLib = new HosePriceFetchLib(filePath);
        List<String> hoseStockCodes = Arrays.asList("VNM", "BTC");
        HoseAdapter hoseAdapter = new HoseAdapter(hoseLib, hoseStockCodes);

        List<StockPrice> actualPrices = hoseAdapter.fetch();
        System.out.println("Actual prices:");
        for (StockPrice price : actualPrices) {
            System.out.println(price);
        }

        List<String> RealhoseStockCodes = Arrays.asList("VNM");
        if (containsAllStockCodes(actualPrices, RealhoseStockCodes)) {
            System.out.println(ANSI_GREEN + "Pass HoseAdapter test 05" + ANSI_RESET);
            count_pass_test++;
        } else {
            System.out.println(ANSI_RED + "Fail HoseAdapter test 05" + ANSI_RESET);
        }
    }
    private static boolean containsAllStockCodes(List<StockPrice> actualPrices, List<String> expectedStockCodes) {
        List<String> actualStockCodes = actualPrices.stream()
            .map(StockPrice::getCode)
            .collect(Collectors.toList());
        return actualStockCodes.containsAll(expectedStockCodes);
    }
    private static void printResult() {
        if (count_pass_test == num_test) {
            System.out.println(ANSI_GREEN + "Pass all test cases" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Fail test cases" + ANSI_RESET);
            System.out.println("Percent pass: " + (count_pass_test * 100) / num_test + "%");
        }
    }
}
