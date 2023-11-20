package edu.hw7.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Counter {
    private final AtomicInteger counter;
    private final List<Thread> threads;

    public Counter() {
        this.counter = new AtomicInteger(0);
        this.threads = new ArrayList<>();
    }

    public void run(Integer countOfThreads, Integer countOfIterationsInThread) {
        this.counter.set(0);
        this.threads.clear();
        log.info("start method run in main");
        for (int i = 0; i < countOfThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < countOfIterationsInThread; j++) {
                    int newCounter = counter.incrementAndGet();
                    log.info("increment value, changed to " + newCounter);
                }
            });
            threads.add(thread);
            thread.start();
        }
    }

    public AtomicInteger getCounter() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
        return counter;
    }
}
