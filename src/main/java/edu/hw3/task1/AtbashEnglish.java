package edu.hw3.task1;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AtbashEnglish {

    protected final List<Character> alphabetSmall = new ArrayList<>();
    protected final List<Character> alphabetBig = new ArrayList<>();
    protected final char firstBigLetter = 'A';
    protected final char lastBigLetter = 'Z';
    protected final char firstSmallLetter = 'a';
    protected final char lastSmallLetter = 'z';

    public AtbashEnglish() {
        log.info("initialise astbash english class");
        char small = firstSmallLetter;
        char big = firstBigLetter;
        while (small <= lastSmallLetter) {
            alphabetSmall.add(small);
            alphabetBig.add(big);
            small++;
            big++;
        }

    }

    public String code(String input) {
        log.info("encrypt {}", input);
        char[] inputString = input.toCharArray();
        char[] encodedString = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            if (isSmallLetter(inputString[i])) {
                encodedString[i] = getEncodedSmallLetter(inputString[i]);
            } else if (isBigLetter(inputString[i])) {
                encodedString[i] = getEncodedBigLetter(inputString[i]);
            } else {
                encodedString[i] = inputString[i];
            }
        }
        log.info("finish encrypt");
        return new String(encodedString);
    }

    private boolean isSmallLetter(Character character) {
        return character >= firstSmallLetter && character <= lastSmallLetter;
    }

    private Character getEncodedSmallLetter(Character character) {
        return alphabetSmall.get(alphabetSmall.size() - 1 - (character - firstSmallLetter));
    }

    private boolean isBigLetter(Character character) {
        return character >= firstBigLetter && character <= lastBigLetter;
    }

    private Character getEncodedBigLetter(Character character) {
        return alphabetBig.get(alphabetBig.size() - 1 - (character - firstBigLetter));
    }

}
