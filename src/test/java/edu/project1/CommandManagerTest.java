package edu.project1;

import edu.project1.commands.CommandManager;
import edu.project1.commands.Exit;
import edu.project1.commands.GuessWord;
import edu.project1.commands.WrongInputException;
import edu.project1.io.MyInput;
import edu.project1.io.MyOutput;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandManagerTest {

    public static Stream<Arguments> getCommandProvider() {
        return Stream.of(
            Arguments.of("help", Exit.class),
            Arguments.of("exit", Exit.class),
            Arguments.of("guess_word", GuessWord.class)
        );
    }

    @ParameterizedTest(name = "Test {index} with user command \"{0}\" should return object of class {1}")
    @MethodSource("getCommandProvider")
    public void getCommand_shouldReturnCommandIfInputCorrect(String command, Class<?> expectedClass) {
        CommandManager commandManager = new CommandManager();
        try {
            Class<?> actualClass =
                commandManager.getCommand(command, new Dictionary(new ArrayList<>()), new MyInput(), new MyOutput())
                    .getClass();

            assertSame(actualClass, expectedClass);
        } catch (WrongInputException e) {
            fail();
        }
    }

    @ParameterizedTest(name = "Test {index} with user command \"{0}\" should throw WrongInputException")
    @ValueSource(strings = {"hh", "", "hhhhhhhhhhhhhhhhhhhhh"})
    public void getCommand_shouldThrowExceptionIfInputIncorrect(String command) {
        CommandManager commandManager = new CommandManager();
        try {
            commandManager.getCommand(command, new Dictionary(new ArrayList<>()), new MyInput(), new MyOutput());

            fail();
        } catch (WrongInputException e) {
            assertTrue(true);
        }
    }

    @ParameterizedTest(name = "Test {index} with null of empty input should throw WrongInputException")
    @NullAndEmptySource
    public void getCommand_shouldThrowExceptionIfInputNull(String command) {
        CommandManager commandManager = new CommandManager();
        try {
            commandManager.getCommand(command, new Dictionary(new ArrayList<>()), new MyInput(), new MyOutput());

            fail();
        } catch (WrongInputException e) {
            assertTrue(true);
        }
    }

}
