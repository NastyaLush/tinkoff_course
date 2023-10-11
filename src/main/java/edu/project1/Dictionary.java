package edu.project1;

import java.util.ArrayList;

public record Dictionary(ArrayList<String> dictionary) {
    public String getRandomWord() {
        return dictionary.get(getRandomIdWord());
    }

    private int getRandomIdWord() {
        return (int) (Math.random() * dictionary.size());
    }

}
