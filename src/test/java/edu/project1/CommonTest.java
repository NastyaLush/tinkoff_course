package edu.project1;

import edu.project1.commands.CommandExecutor;
import edu.project1.commands.CommandManager;
import edu.project1.io.Input;
import edu.project1.io.MyOutput;
import edu.project1.io.Output;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommonTest {

    private static Stream<Arguments> guessWordMethodProvider() {
        return Stream.of(
            Arguments.of(new String[] {"abba"}, new String[] {"guess_word", "abba", "exit"}),
            Arguments.of(new String[] {"abba"}, new String[] {"guess_word", "a", "a", "a", "c", "b", "exit"}),
            Arguments.of(
                new String[] {"iHateLSP"},
                new String[] {"guess_word", "a", "a", "a", "height", "b", "ihatelsp", "exit"}
            ),
            Arguments.of(new String[] {"hello"}, new String[] {"guess_word", "h", "e", "l", "o", "exit"}),
            Arguments.of(
                new String[] {"stAsiK"},
                new String[] {"guess_word", "k", "height", "t", "a", "e", "s", "exit"}
            ),
            Arguments.of(
                new String[] {"Pashalka"},
                new String[] {"guess_word", "o", "o", "o", "o", "o", "pashal", "pashalll", "hh", "he", "pashalka",
                              "exit"
                }
            )
        );
    }

    @ParameterizedTest(name = "Проверка некоректных слов в словаре")
    @ValueSource(strings = {"-ddd", "jj-jj", "gsu4f", "a", ""})
    void validationWord_shouldReturnErrorIfIncorrectWord(String word) {
        ArrayList<String> dictionaryArray = addWordsToDictionary(new String[] {word});
        Output output = new MyOutput();
        Dictionary dictionary = new Dictionary(dictionaryArray);
        CommandExecutor commandExecutor = new CommandExecutor();
        CommandManager commandManager = new CommandManager();

        HangMan hangMan = new HangMan(dictionary, () -> "guess_word", output, commandExecutor, commandManager);

        assertThrows(
            IllegalArgumentException.class,
            hangMan::play
        );
    }

    private ArrayList<String> addWordsToDictionary(String[] words) {
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.addAll(Arrays.asList(words));
        return dictionary;
    }

    @Test
    @DisplayName("Поражение, когда закончились попытки")
    void checkIfALotOfMistakes() {
        final String[] last_string = new String[1];
        ArrayList<String> dictionaryArray = addWordsToDictionary(new String[] {"abba"});
        Input input = new Input() {
            final String[] inputs = {"guess_word", "s", "s", "s", "s", "s", "s", "exit"};
            int counter = 0;

            @Override
            public String read() {
                return inputs[counter++];
            }
        };
        Output output = string -> last_string[0] = string;
        Dictionary dictionary = new Dictionary(dictionaryArray);
        CommandExecutor commandExecutor = new CommandExecutor();
        CommandManager commandManager = new CommandManager();

        HangMan hangMan = new HangMan(dictionary, input, output, commandExecutor, commandManager);
        hangMan.play();

        assertThat(last_string[0].equals(Messages.FAILED_MESSAGE));
    }

    @ParameterizedTest(name = "Iteration #{index} -> Given words = {0}, User Input={1}")
    @MethodSource("guessWordMethodProvider")
    void checkCorrectWork(String[] dictionaryWords, String[] userInput) {
        final String[] last_string = new String[1];
        ArrayList<String> dictionaryArray = addWordsToDictionary(dictionaryWords);
        Input input = new Input() {
            final String[] inputs = userInput;
            int counter = 0;

            @Override
            public String read() {
                return inputs[counter++];
            }
        };
        Output output = string -> last_string[0] = string;
        Dictionary dictionary = new Dictionary(dictionaryArray);
        CommandExecutor commandExecutor = new CommandExecutor();
        CommandManager commandManager = new CommandManager();

        HangMan hangMan = new HangMan(dictionary, input, output, commandExecutor, commandManager);
        hangMan.play();

        assertThat(last_string[0].equals(Messages.SUCCESS_MESSAGE));
    }
}
