package unit_test;

import com.myproject.*;

import java.util.ArrayList;
import java.util.List;

class TestStockFeeder {
    public void test01() {
        StockFeeder x = StockFeeder.getInstance();
        StockFeeder y = StockFeeder.getInstance();
        System.out.println("Hashcode of x: " + x.hashCode());
        System.out.println("Hashcode of y: " + y.hashCode());
        if(x == y) {System.out.println("x and y points to the same allocated memory");}
        else {System.out.println("x and y DO NOT points to the same allocated memory");}
    }

    public void test02() {
        StockFeeder stockFeeder = StockFeeder.getInstance();

        int numStock = 5;
        Stock[] stockArr= new Stock[numStock];
        stockArr[0] = new Stock("HPG", "Hoa Phat group");
        stockArr[1] = new Stock("NKG", "Nam Kim group");
        stockArr[2] = new Stock("HSG", "Hoa Sen group");
        stockArr[3] = new Stock("PLX", "Petrolimex");
        stockArr[4] = new Stock("HTG", "Hoa Tho group");

        for(Stock stock : stockArr) {
            stockFeeder.addStock(stock);
        }
        System.out.println(stockFeeder.getStockList().toString());
    }

    public void test03() {
        StockFeeder feeder = StockFeeder.getInstance();
        int numStock = 3;
        int numViewers = 2;
        Stock[] stockArr= new Stock[numStock];
        List<StockViewer> viewerArr = new ArrayList<StockViewer>();

        stockArr[0] = new Stock("NKG", "Nam Kim group");
        stockArr[1] = new Stock("HSG", "Hoa Sen group");
        stockArr[2] = new Stock("PLX", "Petrolimex");

        viewerArr.add(new StockAlertView(23000, 20000));
        viewerArr.add(new StockRealtimePriceView());
        StockTickerView tickerViewer = new StockTickerView();

        for(Stock stock : stockArr) {
            feeder.addStock(stock);
        }

        feeder.getViewers().put(stockArr[0].getCode(), viewerArr);
        feeder.getViewers().put(stockArr[1].getCode(), viewerArr);
        feeder.getViewers().put(stockArr[2].getCode(), viewerArr);

        System.out.println(feeder.checkRegister("VIC", viewerArr.get(0)));  // False: not in the monitor list
        System.out.println(feeder.checkRegister("HSG", null));  // True
        System.out.println(feeder.checkRegister("PLX", viewerArr.get(0)));  // False: viewer already exists in the viewer list
        System.out.println(feeder.checkRegister("PLX", tickerViewer));  // True

    }

    public void test04() {
        StockFeeder feeder = StockFeeder.getInstance();
        int numStock = 3;
        int numViewers = 2;
        Stock[] stockArr= new Stock[numStock];
        List<StockViewer> viewerArr = new ArrayList<StockViewer>();

        stockArr[0] = new Stock("NKG", "Nam Kim group");
        stockArr[1] = new Stock("HSG", "Hoa Sen group");
        stockArr[2] = new Stock("PLX", "Petrolimex");

        viewerArr.add(new StockAlertView(23000, 20000));
        viewerArr.add(new StockRealtimePriceView());
        StockTickerView tickerViewer = new StockTickerView();

        for(Stock stock : stockArr) {
            feeder.addStock(stock);
        }

        feeder.getViewers().put(stockArr[0].getCode(), viewerArr);
        feeder.getViewers().put(stockArr[1].getCode(), viewerArr);
        feeder.getViewers().put(stockArr[2].getCode(), viewerArr);

        System.out.println(feeder.checkUnregister("VIC", viewerArr.get(0)));  // False: not in the monitor list
        System.out.println(feeder.checkUnregister("HSG", null));  // False: viewer not exist in the viewer list
        System.out.println(feeder.checkUnregister("PLX", viewerArr.get(0)));  // True
        System.out.println(feeder.checkUnregister("PLX", tickerViewer));  // False: viewer not exist in the viewer list

    }

    public void test05() {
        StockFeeder feeder = StockFeeder.getInstance();
        int numStock = 3;
        int numViewers = 3;

        Stock[] stockArr= new Stock[numStock];
        stockArr[0] = new Stock("NKG", "Nam Kim group");
        stockArr[1] = new Stock("HSG", "Hoa Sen group");
        stockArr[2] = new Stock("PLX", "Petrolimex");

        List<StockViewer> viewerArr = new ArrayList<StockViewer>();
        StockViewer viewer1 = new StockRealtimePriceView();
        StockViewer viewer2 = new StockAlertView(25000, 30000);
        StockViewer viewer3 = new StockTickerView();
        viewerArr.add(viewer1);
        viewerArr.add(viewer2);
        viewerArr.add(viewer3);

        for(int i = 0; i < numStock; i++) { feeder.addStock(stockArr[i]); }
        System.out.println(feeder.getStockList().toString());
        System.out.println(feeder.getViewers().toString());

        System.out.println("\nRegistering viewers...");
        feeder.registerViewer(stockArr[0].getCode(), viewerArr.get(0));
        feeder.registerViewer(stockArr[0].getCode(), viewerArr.get(1));
        feeder.registerViewer(stockArr[0].getCode(), viewerArr.get(2));
        feeder.registerViewer(stockArr[0].getCode(), viewerArr.get(2)); // Error
        feeder.registerViewer("VNM", viewerArr.get(2)); // Error

        feeder.registerViewer(stockArr[2].getCode(), viewerArr.get(2));
        System.out.println(feeder.getViewers().toString());

        System.out.println("\nUnregistering viewers...");
        feeder.unregisterViewer(stockArr[0].getCode(), viewerArr.get(0));
        feeder.unregisterViewer(stockArr[0].getCode(), viewerArr.get(1));
        feeder.unregisterViewer(stockArr[2].getCode(), viewerArr.get(2));
        feeder.unregisterViewer(stockArr[2].getCode(), viewerArr.get(2));   // Error
        feeder.unregisterViewer(stockArr[1].getCode(), viewerArr.get(2));   // Error
        feeder.unregisterViewer("HTG", viewerArr.get(2));   // Error
        System.out.println(feeder.getViewers().toString());

    }

    public void test06() {
        StockFeeder feeder = StockFeeder.getInstance();
        int numStock = 3;
        int numViewers = 3;

        Stock[] stockArr= new Stock[numStock];
        stockArr[0] = new Stock("NKG", "Nam Kim group");
        stockArr[1] = new Stock("HSG", "Hoa Sen group");
        stockArr[2] = new Stock("PLX", "Petrolimex");

        List<StockViewer> viewerArr = new ArrayList<StockViewer>();
        StockViewer viewer1 = new StockRealtimePriceView();
        StockViewer viewer2 = new StockAlertView(25000, 30000);
        StockViewer viewer3 = new StockTickerView();
        viewerArr.add(viewer1);
        viewerArr.add(viewer2);
        viewerArr.add(viewer3);

        for(int i = 0; i < numStock; i++) { feeder.addStock(stockArr[i]); }
        feeder.registerViewer(stockArr[0].getCode(), viewerArr.get(0));
        feeder.registerViewer(stockArr[1].getCode(), viewerArr.get(1));
        feeder.registerViewer(stockArr[2].getCode(), viewerArr.get(2));

        System.out.println(feeder.getStockList().toString());
        System.out.println(feeder.getViewers().toString());

        int numStockPrices = 6;
        StockPrice[] prices = new StockPrice[numStockPrices];
        prices[0] = new StockPrice(stockArr[0].getCode(), 25000, 125, 0);
        prices[1] = new StockPrice(stockArr[0].getCode(), 26000, 200, 1);
        prices[2] = new StockPrice(stockArr[0].getCode(), 27000, 350, 2);
        prices[3] = new StockPrice(stockArr[0].getCode(), 21000, 400, 3);
        prices[4] = new StockPrice(stockArr[0].getCode(), 21000, 400, 3);
        prices[5] = new StockPrice(stockArr[0].getCode(), 21000, 400, 3);

        for(int i = 0; i < numStockPrices; i++) {
            feeder.notify(prices[i]);
        }
    }

    public void test07() {
        StockFeeder feeder = StockFeeder.getInstance();
        int numStock = 3;
        int numViewers = 3;

        Stock[] stockArr= new Stock[numStock];
        stockArr[0] = new Stock("NKG", "Nam Kim group");
        stockArr[1] = new Stock("HSG", "Hoa Sen group");
        stockArr[2] = new Stock("PLX", "Petrolimex");

        List<StockViewer> viewerArr = new ArrayList<StockViewer>();
        StockViewer viewer1 = new StockRealtimePriceView();
        StockViewer viewer2 = new StockAlertView(28000, 26000);
        StockViewer viewer3 = new StockTickerView();
        viewerArr.add(viewer1);
        viewerArr.add(viewer2);
        viewerArr.add(viewer3);

        for(int i = 0; i < numStock; i++) { feeder.addStock(stockArr[i]); }
        feeder.registerViewer(stockArr[0].getCode(), viewerArr.get(0));
        feeder.registerViewer(stockArr[1].getCode(), viewerArr.get(1));
        feeder.registerViewer(stockArr[2].getCode(), viewerArr.get(2));

        System.out.println(feeder.getStockList().toString());
        System.out.println(feeder.getViewers().toString());

        int numStockPrices = 5;
        StockPrice[] prices = new StockPrice[numStockPrices];
        prices[0] = new StockPrice(stockArr[1].getCode(), 25000, 125, 0);   // Alert
        prices[1] = new StockPrice(stockArr[1].getCode(), 25999, 200, 1);   // Alert
        prices[2] = new StockPrice(stockArr[1].getCode(), 27000, 350, 2);
        prices[3] = new StockPrice(stockArr[1].getCode(), 26000, 400, 3);
        prices[4] = new StockPrice(stockArr[1].getCode(), 28001, 400, 3);   // Alert


        for(int i = 0; i < numStockPrices; i++) {
            feeder.notify(prices[i]);
        }
    }


}

public class tc02 {
    public static void main(String[] args) {
        TestStockFeeder test = new TestStockFeeder();
        test.test07();
    }
}
