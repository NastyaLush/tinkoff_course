package edu.project1;

import edu.hw1.EvenArrayUtils;
import edu.hw1.Task6;
import edu.project1.commands.CommandExecutor;
import edu.project1.commands.CommandManager;
import edu.project1.commands.GuessWord;
import edu.project1.io.Input;
import edu.project1.io.MyInput;
import edu.project1.io.MyOutput;
import edu.project1.io.Output;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SampleTest {
    private ArrayList<String> addWordsToDictionary(String[] words){
        ArrayList<String> dictionary = new ArrayList<>();
        for(String word: words){
            dictionary.add(word);
        }
        return dictionary;
    }
    private HangMan getHangMan(String[] words){
        ArrayList<String> dictionaryArray = addWordsToDictionary(words);
        Input input = new MyInput();
        Output output = new MyOutput();
        Dictionary dictionary = new Dictionary(dictionaryArray);
        CommandExecutor commandExecutor = new CommandExecutor();
        CommandManager commandManager = new CommandManager();

        return new HangMan(dictionary, input, output, commandExecutor, commandManager);

    }
    @ParameterizedTest(name = "Проверка некоректных слов в словаре")
    @ValueSource(strings = {"-ddd","jj-jj", "gsu4f", "aa", "a", ""})
    void checkIncorrectWord(String word) {
        HangMan hangMan =  getHangMan(new String[] {word});
        assertThrows(
            IllegalArgumentException.class,
            hangMan::play
        );
    }
}
