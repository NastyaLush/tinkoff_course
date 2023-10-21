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
    private final Integer minSizeWordDefault = 2;
    private final Integer minCountOfMistakesDefault = 6;
    private final WordManager word;
    private Integer mistakes;

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
        this.word = new WordManager(minSizeWord);
        this.typedLetters = new ArrayList<>();
    }

    protected GuessWord(Dictionary dictionary, Input input, Output output) {
        super(COMMAND_NAME);
        this.dictionary = dictionary;
        this.input = input;
        this.output = output;
        this.countOfMistakes = minCountOfMistakesDefault;
        this.word = new WordManager(minSizeWordDefault);
        this.typedLetters = new ArrayList<>();
    }

    @Override
    protected boolean execute() {
        mistakes = 0;
        String userInput;

        output.write(START_MESSAGE);

        word.setGuessedWord(dictionary.getRandomWord());

        output.write(GUESS_MESSAGE);

        while (!word.isWordGuessed() && mistakes < countOfMistakes) {

            output.write(WORD_MESSAGE + word.getGuessingWord());
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

        if (word.isWordGuessed()) {
            output.write(SUCCESS_MESSAGE + word.getGuessingWord());
        } else {
            output.write(FAILED_MESSAGE);
        }

        return true;
    }

    private boolean isExitCommand(String command) {
        if (command.equals("exit")) {
            return new Exit().execute();
        }
        return true;
    }

    private void handleWord(String userInput) {
        if (!word.isWordGuessed(userInput) && !word.isTypo(userInput)) {
            handleLetter(userInput.charAt(0));
        }
    }

    private void handleLetter(Character letter) {
        if (typedLetters.contains(letter)) {
            mistakes++;
        } else {
            typedLetters.add(letter);
            if (!word.updateWord(letter)) {
                mistakes++;
            }
        }
    }
}
