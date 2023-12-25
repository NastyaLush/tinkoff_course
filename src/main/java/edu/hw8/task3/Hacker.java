package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;

@Log4j2 public class Hacker {

    private final Map<String, String> data;
    private final Map<String, String> passwords = new ConcurrentHashMap<>();
    private final AtomicInteger passNumber = new AtomicInteger(0);
    private final char[] symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private final Integer countOfPossibleCharacters = 62;

    public Hacker(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> hack(Integer countOfThreads) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < countOfThreads; i++) {
            Thread thread = new Thread(() -> {
                while (!data.isEmpty()) {
                    String pass = nextPassword();
                    String hash = getHash(pass);
                    if (data.containsKey(hash)) {
                        log.info("find password for " + data.get(hash) + " " + pass);
                        passwords.put(data.get(hash), pass);
                        data.remove(hash);
                    }
                }
            });
            threadList.add(thread);
            thread.start();
        }
        for (int i = 0; i < countOfThreads; i++) {
            threadList.get(i).join();
        }

        return passwords;
    }

    private String nextPassword() {
        StringBuilder pass = new StringBuilder();
        int current = passNumber.getAndIncrement();
        do {
            pass.append(symbols[current % countOfPossibleCharacters]);
            current /= countOfPossibleCharacters;
        } while (current != 0);
        return pass.toString();
    }

    private String getHash(String pass) {
        return DigestUtils.md5Hex(pass).toLowerCase();
    }
}
