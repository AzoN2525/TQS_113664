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

}
