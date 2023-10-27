package edu.hw3.task6;

import java.util.PriorityQueue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class StockMarketImpl implements StockMarket {

    private final PriorityQueue<Stock> queue;

    public StockMarketImpl() {
        this.queue = new PriorityQueue<>();
    }

    @Override
    public void add(Stock stock) {
        log.info("add {}", stock);
        queue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        log.info("remove {}", stock);
        queue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        log.info("get most valuable stock");
        return queue.peek();
    }

}
