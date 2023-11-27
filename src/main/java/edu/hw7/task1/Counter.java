package edu.hw7.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.log4j.Log4j2;

@Log4j2 public class Counter {

    public AtomicInteger run(Integer countOfThreads, Integer countOfIterationsInThread) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        List<Thread> threads = new ArrayList<>();
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
        for (Thread thread : threads) {
            thread.join();
        }
        return counter;
    }

}
