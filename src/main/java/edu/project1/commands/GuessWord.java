package edu.project1.commands;

import edu.project1.Dictionary;
import edu.project1.io.Input;
import edu.project1.io.Output;
import java.util.ArrayList;
import static edu.project1.Messages.ALREADY_TYPED_MESSAGE;
import static edu.project1.Messages.FAILED_MESSAGE;
import static edu.project1.Messages.GUESS_MESSAGE;
import static edu.project1.Messages.MISTAKE_MESSAGE;
import static edu.project1.Messages.MISTAKE_MESSAGE_COUNT;
import static edu.project1.Messages.START_MESSAGE;
import static edu.project1.Messages.SUCCESS_MESSAGE;
import static edu.project1.Messages.WORD_MESSAGE;
import static edu.project1.Messages.WRITE_MESSAGE;

public class GuessWord extends AbstractCommand {
    private static final String COMMAND_NAME = "guess_word";
    private final Dictionary dictionary;
    private final Input input;
    private final Output output;
    private final ArrayList<Character> typedLetters;
    private final Integer countOfMistakes;
    private final Integer minSizeWord;
    private final Integer minSizeWordDefault = 2;
    private final Integer minCountOfMistakesDefault = 6;
    private Integer mistakes;
    private String guessedWord;
    private String guessingWord;

    protected GuessWord(
        Dictionary dictionary,
        Input input,
        Output output,
        Integer countOfMistakes,
        Integer minSizeWord
    ) {
        super(COMMAND_NAME);
        this.dictionary = dictionary;
        this.input = input;
        this.output = output;
        this.countOfMistakes = countOfMistakes;
        this.minSizeWord = minSizeWord;
        this.typedLetters = new ArrayList<>();
    }

    protected GuessWord(Dictionary dictionary, Input input, Output output) {
        super(COMMAND_NAME);
        this.dictionary = dictionary;
        this.input = input;
        this.output = output;
        this.countOfMistakes = minCountOfMistakesDefault;
        this.minSizeWord = minSizeWordDefault;
        this.typedLetters = new ArrayList<>();
    }

    @Override
    protected boolean execute() {
        mistakes = 0;
        String userInput;

        output.write(START_MESSAGE);

        guessedWord = dictionary.getRandomWord();
        validateWord(guessedWord);

        guessingWord = this.getGuessingDefaultWord(guessedWord.length());
        output.write(GUESS_MESSAGE);

        while (!isWordGuessed(guessingWord) && mistakes < countOfMistakes) {

            output.write(WORD_MESSAGE + guessingWord);
            output.write(MISTAKE_MESSAGE + mistakes);
            output.write(MISTAKE_MESSAGE_COUNT + countOfMistakes);
            output.write(ALREADY_TYPED_MESSAGE + typedLetters);
            output.write(WRITE_MESSAGE);

            userInput = input.read();
            if (!isExitCommand(userInput)) {
                return false;
            }
            handleWord(userInput);
        }

        if (isWordGuessed(guessingWord)) {
            output.write(SUCCESS_MESSAGE + guessedWord);
        } else {
            output.write(FAILED_MESSAGE);
        }

        return true;
    }

    private void handleWord(String userInput) {
        if (isWordsHaveSameLength(userInput) && isWordGuessed(userInput)) {
            guessingWord = guessedWord;
        } else if (!isTypo(userInput)) {
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
        char[] guessingWordChars = guessingWord.toCharArray();
        for (int i = 0; i < guessedWord.length(); i++) {
            if (guessedWord.charAt(i) == letter) {
                guessingWordChars[i] = letter;
            }
        }
        guessingWord = new String(guessingWordChars);

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

    private boolean isWordGuessed(String userWord) {
        return guessedWord.equals(userWord);
    }

    private void validateWord(String word) {

        if (word.length() < minSizeWord || !word.matches("^[a-zA-Z]*$")) {
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
