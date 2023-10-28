package edu.project1;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class DictionaryTest {

    @Test
    public void getRandomWord_shouldReturnRandomWord() {
        Dictionary dictionary = new Dictionary(addWordsToDictionary(new String[] {"ddd", "hhhh"}));
        String word = dictionary.getRandomWord();
        String secondWord;

        do {
            secondWord = dictionary.getRandomWord();
        }
        while (word.equals(secondWord));
    }

    private ArrayList<String> addWordsToDictionary(String[] words) {
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.addAll(Arrays.asList(words));
        return dictionary;
    }

}
