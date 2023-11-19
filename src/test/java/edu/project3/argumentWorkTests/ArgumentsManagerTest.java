package edu.project3.argumentWorkTests;

import edu.project3.argumentWork.ArgumentsManager;
import edu.project3.output.OutputType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class ArgumentsManagerTest {

    public static Stream<Arguments> dateProvider() {
        return Stream.of(
                Arguments.of(
                        "2011-12-03T10:15:30", LocalDateTime.of(2011, 12, 3, 10, 15, 30),
                        "2011-12-03", LocalDateTime.of(2011, 12, 3, 0, 0, 0),
                        "2011/12/03", null
                )
        );
    }

    public static Stream<Arguments> formatProvider() {
        return Stream.of(
                Arguments.of(
                        "markdown", OutputType.MARKDOWN,
                        "adoc", OutputType.ADOC
                )
        );
    }

    @ParameterizedTest(name = "The date {0} should be converted to {1}")
    @MethodSource("dateProvider")
    public void setArgumentFrom_shouldParsStringToDate(String givenDate, LocalDateTime expectedLocalDateTime) {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setArgumentFrom(givenDate);

        LocalDateTime actualLocalDateTime = argumentsManager.getArgumentFrom();

        assertEquals(expectedLocalDateTime, actualLocalDateTime);

    }

    @Test()
    @DisplayName("setArgumentFromShould Throw Error if already has this field")
    public void setArgumentFrom_shouldThrowErrorIfDateAlreadyAdded() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String from = "2011-12-03T10:15:30";
        argumentsManager.setArgumentFrom(from);

        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setArgumentFrom(from));
    }

    @ParameterizedTest(name = "The date {0} should be converted to {1}")
    @MethodSource("dateProvider")
    public void setArgumentTo_shouldParsStringToDate(String givenDate, LocalDateTime expectedLocalDateTime) {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setArgumentTo(givenDate);

        LocalDateTime actualLocalDateTime = argumentsManager.getArgumentTo();

        assertEquals(expectedLocalDateTime, actualLocalDateTime);

    }

    @Test()
    @DisplayName("setArgumentTo should throw error if already has this field")
    public void setArgumentTo_shouldThrowErrorIfDateAlreadyAdded() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String from = "2011-12-03T10:15:30";
        argumentsManager.setArgumentTo(from);

        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setArgumentTo(from));
    }

    @ParameterizedTest(name = "The input {0} should be converted to {1}")
    @MethodSource("formatProvider")
    public void setArgumentFormat_shouldParsFormatIfDataCorrect(String givenInput, OutputType expectedOutputType) {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setArgumentFormat(givenInput);

        OutputType actualLocalDateTime = argumentsManager.getArgumentFormat();

        assertEquals(expectedOutputType, actualLocalDateTime);

    }

    @Test()
    @DisplayName("setArgumentFormat should throw error if already has this field")
    public void setArgumentFormat_shouldThrowErrorIfDateAlreadyAdded() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String format = "adoc";
        argumentsManager.setArgumentFormat(format);

        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setArgumentFormat(format));
    }

    @Test()
    @DisplayName("setArgumentFormat should throw error if there is no format")
    public void setArgumentFormat_shouldThrowErrorIfThereIsNoFormat() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String format = "adocc";


        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setArgumentFormat(format));
    }

    @Test()
    @DisplayName("validate should throw error if there is no path")
    public void validate_shouldThrowErrorIfThereIsNoThisPath() {
        ArgumentsManager argumentsManager = new ArgumentsManager();


        assertThrows(IllegalArgumentException.class, argumentsManager::validate);
    }

    @Test()
    @DisplayName("setArgumentPath should throw error if path already")
    public void setArgumentPath_shouldThrowErrorIfPathAlreadyExist() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String path = "a";
        argumentsManager.setArgumentPath(path);


        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setArgumentPath(path));
    }

    @Test()
    @DisplayName("setArgumentPath should add path if path not exist")
    public void setArgumentPath_shouldAddPathIfPathNotExist() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String expectedPath = "a";
        argumentsManager.setArgumentPath(expectedPath);

        String actualPath = argumentsManager.getArgumentPath();

        assertEquals(expectedPath, actualPath);
    }

    @Test()
    @DisplayName("validate should work correctly if path exist")
    public void validate_shouldWorkCorrectlyIfThereIsPath() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setArgumentPath("a");


        argumentsManager.validate();
    }
}
