package edu.project1.commands;

import edu.project1.Dictionary;
import edu.project1.io.Input;
import edu.project1.io.Output;
import java.util.ArrayList;

public class GuessWord extends AbstractCommand {
    private final Dictionary dictionary;
    private final Input input;
    private final Output output;
    private final Integer countOfMistakes;
    private final ArrayList<Character> typedLetters;
    private final Integer minSizeWord;
    private final Integer MIN_SIZE_WORD_DEFAULT = 2;
    private final Integer MIN_COUNT_OF_MISTAKES_DEFAULT = 6;
    private final String START_MESSAGE = "Start game";
    private final String GUESS_MESSAGE = "The word is guessed";
    private final String WORD_MESSAGE = "The word: ";
    private final String MISTAKE_MESSAGE = "Count of mistakes: ";
    private final String MISTAKE_MESSAGE_COUNT = "Allowed count of mistakes: ";
    private final String WRITE_MESSAGE = "Write a letter or a world";
    private final String SUCCESS_MESSAGE = "HOORAY, you've guessed the word: ";
    private final String FAILED_MESSAGE = "OOps, you didn't guess the word";
    private final String AlREADY_TYPED_MESSAGE = "Typed letters: ";
    private Integer mistakes;
    private String guessedWord;
    private String guessing_word;

    protected GuessWord(Dictionary dictionary, Input input, Output output) {
        super("guess_word");
        this.dictionary = dictionary;
        this.input = input;
        this.output = output;
        this.countOfMistakes = MIN_COUNT_OF_MISTAKES_DEFAULT;
        this.minSizeWord = MIN_SIZE_WORD_DEFAULT;
        this.typedLetters = new ArrayList<>();
    }

    protected GuessWord(
        Dictionary dictionary,
        Input input,
        Output output,
        Integer countOfMistakes,
        Integer minSizeWord
    ) {
        super("guess_word");
        this.dictionary = dictionary;
        this.input = input;
        this.output = output;
        this.countOfMistakes = countOfMistakes;
        this.minSizeWord = minSizeWord;
        this.typedLetters = new ArrayList<>();
    }

    @Override
    protected boolean execute() {
        mistakes = 0;
        String userInput;

        output.write(START_MESSAGE);

        guessedWord = dictionary.getRandomWord();
        validateWord(guessedWord);

        guessing_word = this.getGuessingDefaultWord(guessedWord.length());
        output.write(GUESS_MESSAGE);

        while (!isWordGuessed() && mistakes < countOfMistakes) {

            output.write(WORD_MESSAGE + guessing_word);
            output.write(MISTAKE_MESSAGE + mistakes);
            output.write(MISTAKE_MESSAGE_COUNT + countOfMistakes);
            output.write(AlREADY_TYPED_MESSAGE + typedLetters);
            output.write(WRITE_MESSAGE);

            userInput = input.read();
            if (!isExitCommand(userInput)) {
                return false;
            }
            handleWord(userInput);
        }

        if (isWordGuessed()) {
            output.write(SUCCESS_MESSAGE + guessedWord);
        } else {
            output.write(FAILED_MESSAGE);
        }

        return true;
    }

    private void handleWord(String userInput) {
        if (isWordsHaveSameLength(userInput) && isWordGuessed()) {
            guessing_word = guessedWord;
        }
        if (!isTypo(userInput)) {
            handleLetter(userInput.charAt(0));
        }
    }

    private void handleLetter(Character letter) {
        if (typedLetters.contains(letter)) {
            mistakes++;
        } else {
            typedLetters.add(letter);
            if (!guessedWord.contains(letter.toString())) {
                mistakes++;
            } else {
                updateGuessingWord(letter);
            }
        }
    }

    private void updateGuessingWord(Character letter) {
        char[] guessingWordChars = guessing_word.toCharArray();
        for (int i = 0; i < guessedWord.length(); i++) {
            if (guessedWord.charAt(i) == letter) {
                guessingWordChars[i] = letter;
            }
        }
        guessing_word = new String(guessingWordChars);

    }

    private boolean isWordsHaveSameLength(String userInput) {
        return guessedWord.length() == userInput.length();
    }

    private boolean isLengthDifferentIsOne(String word1, String word2) {
        return Math.abs(word1.length() - word2.length()) == 1;
    }

    private boolean isWordLetter(String word) {
        return word.length() == 1;
    }

    private boolean isTypo(String userInput) {
        return isLengthDifferentIsOne(guessedWord, userInput) || !isWordLetter(userInput);
    }

    private boolean isWordGuessed() {
        if (guessedWord.equals(guessing_word)) {
            mistakes++;
            return false;
        }
        return true;
    }

    private void validateWord(String word) {
        if (word.length() < minSizeWord && !word.matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("The word size must be more than " + minSizeWord);
        }
    }

    private String getGuessingDefaultWord(int len) {
        return "*".repeat(Math.max(0, len));
    }

    private boolean isExitCommand(String command) {
        if (command.equals("exit")) {
            return new Exit().execute();
        }
        return true;
    }
}
