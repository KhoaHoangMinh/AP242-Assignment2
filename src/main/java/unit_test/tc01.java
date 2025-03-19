package unit_test;

import com.myproject.*;

class TestRealTime {
    public void test01() {
        int numStock = 12;
        StockPrice[] arr = new StockPrice[numStock];

        arr[0] = new StockPrice("HPG", 26000, 100, 0);
        arr[1] = new StockPrice("NKG", 18000, 100, 0);
        arr[2] = new StockPrice("HTG", 50000, 10, 0);

        arr[3] = new StockPrice("HPG", 25000, 200, 10);
        arr[4] = new StockPrice("NKG", 19000, 200, 10);
        arr[5] = new StockPrice("HTG", 49800, 25, 10);

        arr[6] = new StockPrice("HPG", 25500, 5000, 20);
        arr[7] = new StockPrice("NKG", 20500, 5000, 20);
        arr[8] = new StockPrice("HTG", 4800, 1000, 20);

        arr[9] = new StockPrice("HPG", 25500, 5000, 20);
        arr[10] = new StockPrice("NKG", 20500, 5000, 20);
        arr[11] = new StockPrice("HTG", 999999, 1000, 20);

        StockRealtimePriceView stockRealtimePriceView = new StockRealtimePriceView();
        for(int i = 0; i < numStock; i++) {
            stockRealtimePriceView.onUpdate(arr[i]);
        }
        System.out.println(stockRealtimePriceView.getLastPrices().toString());
    }
}

class TestStockAlert {
    public void test01() {
        double highThreshold = 30000;
        double lowThreshold = 25000;
        StockAlertView stockAlertView = new StockAlertView(highThreshold, lowThreshold);

        int numStock = 12;
        StockPrice[] arr = new StockPrice[numStock];

        arr[0] = new StockPrice("HPG", 26000, 100, 0);  // No alert (Above low threshold, below high)
        arr[1] = new StockPrice("HPG", 24000, 100, 1);  // Alert (Below low threshold)
        arr[2] = new StockPrice("HPG", 25000, 100, 2);  // No alert (Equal to low threshold)
        arr[3] = new StockPrice("HPG", 20000, 100, 3);  // Alert (Below low threshold)
        arr[4] = new StockPrice("HPG", 27000, 100, 4);  // No alert (Above low threshold, below high)
        arr[5] = new StockPrice("HPG", 28000, 100, 5);  // No alert (Above low threshold, below high)
        arr[6] = new StockPrice("HPG", 29000, 100, 6);  // No alert (Above low threshold, below high)
        arr[7] = new StockPrice("HPG", 31000, 100, 7);  // No alert (Above high threshold)
        arr[8] = new StockPrice("HPG", 24000, 100, 8);  // Alert (Below low threshold)
        arr[9] = new StockPrice("HPG", 22000, 100, 9);  // Alert (Below low threshold)
        arr[10] = new StockPrice("HPG", 25001, 100, 10); // No alert (Above low threshold)
        arr[11] = new StockPrice("HPG", 24000, 100, 11); // Alert (Below low threshold)

        // Test all stock prices and trigger the alert
        for (int i = 0; i < numStock; i++) {
            stockAlertView.onUpdate(arr[i]);
            System.out.println(stockAlertView.getLastAlertedPrice().toString());
        }

    }

    public void test02() {
        double highThreshold = 30000;
        double lowThreshold = 25000;
        StockAlertView stockAlertView = new StockAlertView(highThreshold, lowThreshold);

        int numStock = 12;
        StockPrice[] arr = new StockPrice[numStock];

        // 6 HPG stock prices with varying prices
        arr[0] = new StockPrice("HPG", 26000, 100, 0);  // No alert (Above low threshold, below high)
        arr[1] = new StockPrice("HPG", 24000, 100, 1);  // Alert (Below low threshold)
        arr[2] = new StockPrice("HPG", 25000, 100, 2);  // No alert (Equal to low threshold)
        arr[3] = new StockPrice("HPG", 20000, 100, 3);  // Alert (Below low threshold)
        arr[4] = new StockPrice("HPG", 27000, 100, 4);  // No alert (Above low threshold, below high)
        arr[5] = new StockPrice("HPG", 28000, 100, 5);  // No alert (Above low threshold, below high)

        // 6 GEE stock prices with varying prices
        arr[6] = new StockPrice("GEE", 24000, 100, 6);  // Alert (Below low threshold)
        arr[7] = new StockPrice("GEE", 25000, 100, 7);  // No alert (Equal to low threshold)
        arr[8] = new StockPrice("GEE", 22000, 100, 8);  // Alert (Below low threshold)
        arr[9] = new StockPrice("GEE", 26000, 100, 9);  // No alert (Above low threshold, below high)
        arr[10] = new StockPrice("GEE", 28000, 100, 10); // No alert (Above low threshold, below high)
        arr[11] = new StockPrice("HPG", 35000, 100, 11); // No alert (Above low threshold, below high)

        // Test all stock prices and trigger the alert
        for (int i = 0; i < numStock; i++) {
            stockAlertView.onUpdate(arr[i]);
            System.out.println(stockAlertView.getLastAlertedPrice().toString());
        }
    }

}

public class tc01 {
    public static void main(String[] args) {
        System.out.println("Unit test");
        TestRealTime test2 = new TestRealTime();
        TestStockAlert test3 = new TestStockAlert();

        test3.test02();
    }
}