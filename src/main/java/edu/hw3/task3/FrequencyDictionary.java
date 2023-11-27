package edu.hw3.task3;

import java.util.ArrayList;
import java.util.HashMap;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FrequencyDictionary {

    public <T> HashMap<T, Integer> freqDict(ArrayList<T> dictionary) {
        log.info("begin execute frequency dictionary");
        HashMap<T, Integer> freqDictionary = new HashMap<>();
        for (T value : dictionary) {
            if (freqDictionary.containsKey(value)) {
                freqDictionary.put(value, freqDictionary.get(value) + 1);
            } else {
                freqDictionary.put(value, 1);
            }
        }
        log.info("finish execute frequency dictionary");
        return freqDictionary;
    }

}
