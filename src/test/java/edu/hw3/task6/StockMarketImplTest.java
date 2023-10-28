package edu.hw3.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockMarketImplTest {

    @Test
    @DisplayName("should return most variable stock")
    public void mostValuableStock_shouldReturnMostValuableStock() {
        Stock stock = new Stock("hh", 6f);
        Stock stock2 = new Stock("hh", 7f);
        StockMarketImpl stockMarket = getStockMarket();
        stockMarket.remove(stock);
        Stock expectedStock = stock2;

        Stock actualStock = stockMarket.mostValuableStock();

        assertEquals(expectedStock, actualStock);

    }

    private StockMarketImpl getStockMarket() {
        Stock stock = new Stock("hh", 6f);
        Stock stock0 = new Stock("hh", 6f);
        Stock stock1 = new Stock("hha", 6f);
        Stock stock2 = new Stock("hh", 7f);
        Stock stock3 = new Stock("hah", 3f);
        Stock stock4 = new Stock("hh0", 3f);

        StockMarketImpl stockMarket = new StockMarketImpl();
        stockMarket.add(stock);
        stockMarket.add(stock);
        stockMarket.add(stock0);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        stockMarket.add(stock4);
        return stockMarket;
    }

    @Test
    @DisplayName("Should remove element correctly")
    public void remove_shouldAddElement() {
        Stock stock = new Stock("hh", 6f);
        StockMarketImpl stockMarket = getStockMarket();
        stockMarket.remove(stock);
        Integer expectedSize = 6;

        Integer actualSize = stockMarket.getQueue().size();

        assertEquals(expectedSize, actualSize);

    }

    @Test
    @DisplayName("Should add element correctly")
    public void add_shouldAddElement() {
        Integer expectedSize = 7;
        StockMarketImpl stockMarket = getStockMarket();

        Integer actualSize = stockMarket.getQueue().size();

        assertEquals(expectedSize, actualSize);

    }
}
