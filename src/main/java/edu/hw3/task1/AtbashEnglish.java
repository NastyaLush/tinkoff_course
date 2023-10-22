package edu.hw3.task1;

import java.util.ArrayList;

public class AtbashEnglish {

    protected final ArrayList<Character> alphabetSmall = new ArrayList<>();
    protected final ArrayList<Character> alphabetBig = new ArrayList<>();
    protected final char firstBigLetter = 'A';
    protected final char lastBigLetter = 'Z';
    protected final char firstSmallLetter = 'a';
    protected final char lastSmallLetter = 'z';

    public AtbashEnglish() {
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
        return new String(encodedString);
    }

    private boolean isSmallLetter(Character character) {
        return character >= firstSmallLetter && character <= lastSmallLetter;
    }

    private Character getEncodedSmallLetter(Character character) {
        return alphabetSmall.get(alphabetSmall.size() - (character - firstSmallLetter));
    }

    private boolean isBigLetter(Character character) {
        return character >= firstBigLetter && character <= lastBigLetter;
    }

    private Character getEncodedBigLetter(Character character) {
        return alphabetBig.get(alphabetBig.size() - (character - firstBigLetter));
    }

}
