package edu.hw8.task1.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;

public class QuatotationService {

    private static final String REGEX_ANY_SYMBOLS = ".*";

    @Getter private final List<String> quotes = new ArrayList<>();
    private final String noAnswerPhrase = "There is no quote with this word";
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public String getQuote(String word) {
        if (word == null) {
            return noAnswerPhrase;
        }
        reentrantReadWriteLock.readLock().lock();
        try {
            for (String quote : quotes) {

                if (quote.matches(REGEX_ANY_SYMBOLS + " " + word + " " + REGEX_ANY_SYMBOLS) || quote.matches(
                    word + " " + REGEX_ANY_SYMBOLS) || quote.matches(
                    REGEX_ANY_SYMBOLS + " " + word)) {
                    return quote;
                }
            }
            return noAnswerPhrase;
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
