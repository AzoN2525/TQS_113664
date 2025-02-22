package com.example;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio{
    
    private IStockMarketService stockMarket;
    private List<Stock> stocks = new ArrayList<Stock>();

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue(){
        double value = 0;
        for (Stock stock : stocks) {
            value += stockMarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }
        return value;
    }

    public List<Stock> mostValuableStocks(int topN) {

        if(topN <= 0) {
            return new ArrayList<Stock>();
        }

        List<Stock> mostValuable = new ArrayList<Stock>();
        List<Stock> copy = new ArrayList<Stock>(stocks);

        for (int i = 0; i < topN; i++) {
            Stock max = copy.get(0);
            for (Stock stock : copy) {
                if (stockMarket.lookUpPrice(stock.getLabel()) * stock.getQuantity() > stockMarket.lookUpPrice(max.getLabel()) * max.getQuantity()) {
                    max = stock;
                }
            }
            mostValuable.add(max);
            copy.remove(max);
        }

        return mostValuable;
        
    }

}
