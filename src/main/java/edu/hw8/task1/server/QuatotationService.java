package edu.hw8.task1.server;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class QuatotationService {

    private final ArrayList<String> quotes = new ArrayList<>();
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public String getQuote(String word) {
        reentrantReadWriteLock.readLock().lock();
        try {
            for (String quote : quotes) {
                if (quote.contains(word)) {
                    return quote;
                }
            }
            return "There is no quote with this word";
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public void addQuote(String quote) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            quotes.add(quote);
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
}
