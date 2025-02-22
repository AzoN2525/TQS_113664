
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.IStockMarketService;
import com.example.Stock;
import com.example.StocksPortfolio;

@ExtendWith(MockitoExtension.class)
public class StockPortfolioTest {
    
    @Mock
    IStockMarketService stockMarket; // Prepare a mock to substitute the remote service

    @InjectMocks   
    StocksPortfolio portfolio;      // Create a portfolio instance and annotate it for injection    


    @Test
    void getTotalValueTest(){

        // Load the mock with the proper expectations
        when(stockMarket.lookUpPrice("EBAY")).thenReturn(4.0);
        when(stockMarket.lookUpPrice("MSFT")).thenReturn(3.0);
        when(stockMarket.lookUpPrice("GOOGL")).thenReturn(5.0);  // Will not be used
        
        // Execute the tested method
        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 3));
        double result = portfolio.totalValue();


        // Verify the result and the use of the mock
        assertEquals(17.0, result);
        verify(stockMarket, times(2)).lookUpPrice(anyString());
    }

    

}
