package edu.project1.commands;

public class WordManager {

    private final Integer minSizeWord;
    private String guessedWord;
    private String guessingWord;

    protected WordManager(Integer minSizeWord) {
        this.minSizeWord = minSizeWord;
    }

    protected String getGuessingWord() {
        return guessingWord;
    }

    protected void setGuessedWord(String guessedWord) {
        this.guessedWord = guessedWord;
        validateWord(guessedWord);
        guessingWord = getGuessingDefaultWord(guessedWord.length());
    }

    private void validateWord(String word) {

        if (word.length() < minSizeWord || !word.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("The word size must be more than " + minSizeWord);
        }
    }

    private String getGuessingDefaultWord(int len) {
        return "*".repeat(Math.max(0, len));
    }

    protected boolean isWordGuessed() {
        return isWordGuessed(guessingWord);
    }

    protected boolean isWordGuessed(String word) {
        if (guessedWord.equals(word)) {
            guessingWord = guessedWord;
            return true;
        }
        return false;
    }

    protected boolean isTypo(String userInput) {
        return isLengthDifferentIsOne(guessedWord, userInput) || !isWordLetter(userInput);
    }

    protected boolean isLengthDifferentIsOne(String word1, String word2) {
        return Math.abs(word1.length() - word2.length()) == 1;
    }

    private boolean isWordLetter(String word) {
        return word.length() == 1;
    }

    protected boolean updateWord(Character letter) {
        if (guessedWord.contains(letter.toString())) {
            char[] guessingWordChars = guessingWord.toCharArray();
            for (int i = 0; i < guessedWord.length(); i++) {
                if (guessedWord.charAt(i) == letter) {
                    guessingWordChars[i] = letter;
                }
            }
            guessingWord = new String(guessingWordChars);
            return true;
        }
        return false;
    }

}
