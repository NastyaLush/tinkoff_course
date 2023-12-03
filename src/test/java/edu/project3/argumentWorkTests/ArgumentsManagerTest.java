package edu.project3.argumentWorkTests;

import edu.project3.argumentWork.ArgumentsManager;
import edu.project3.output.OutputType;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    public static Stream<Arguments> argumentsProvider() {
        return Stream.of(
            Arguments.of(
                new ArgumentsManager("path", null, null, null),
                new String[] {"--path", "path"}
            ),
            Arguments.of(
                new ArgumentsManager(null, null, null, OutputType.MARKDOWN),
                new String[] {"--format", "markdown"}
            ),
            Arguments.of(
                new ArgumentsManager(null, null, null, OutputType.ADOC),
                new String[] {"--format", "adoc"}
            ),
            Arguments.of(
                new ArgumentsManager(null, LocalDateTime.of(2011, 12, 3, 10, 15, 30), null, null),
                new String[] {"--from", "2011-12-03T10:15:30"}
            ), Arguments.of(
                new ArgumentsManager(null, LocalDateTime.of(2011, 12, 3, 10, 15, 30), null, null),
                new String[] {"--from", "2011-12-03T10:15:30"}
            ),
            Arguments.of(
                new ArgumentsManager(null, null, LocalDateTime.of(2011, 12, 3, 10, 15, 30), null),
                new String[] {"--to", "2011-12-03T10:15:30"}
            ),
            Arguments.of(
                new ArgumentsManager(
                    "path",
                    LocalDateTime.of(2011, 12, 3, 10, 15, 30),
                    LocalDateTime.of(2011, 12, 3, 10, 15, 30),
                    OutputType.MARKDOWN
                ),
                new String[] {"--path", "path", "--format", "markdown", "--from", "2011-12-03T10:15:30", "--to",
                              "2011-12-03T10:15:30"}
            )
        );
    }

    public static Stream<Arguments> wrongArgumentsProvider() {
        return Stream.of(
            Arguments.of((Object) new String[] {"--path", "path", "--path", "path2"}),
            Arguments.of((Object) new String[] {"--format", "adoc", "--format", "adoc"}),
            Arguments.of((Object) new String[] {"--format", "format"}),
            Arguments.of((Object) new String[] {"--from", "2011-12-03", "--from", "2011-12-03"}),
            Arguments.of((Object) new String[] {"--from", "12-03-2014"}),
            Arguments.of((Object) new String[] {"--to", "2011-12-03", "--to", "2011-12-03"}),
            Arguments.of((Object) new String[] {"--to", "12-03-2014"}),
            Arguments.of((Object) new String[] {"--path", "path", "--flag", "true"}),
            Arguments.of((Object) new String[] {"--path", "path", "--path"})
        );
    }

    @ParameterizedTest(name = "The date {0} should be converted to {1}")
    @MethodSource("dateProvider")
    public void setArgumentFrom_shouldParsStringToDate(String givenDate, LocalDateTime expectedLocalDateTime) {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setDateFrom(givenDate);

        LocalDateTime actualLocalDateTime = argumentsManager.getDateFrom();

        assertEquals(expectedLocalDateTime, actualLocalDateTime);

    }

    @Test()
    @DisplayName("setArgumentFromShould Throw Error if already has this field")
    public void setArgumentFrom_shouldThrowErrorIfDateAlreadyAdded() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String from = "2011-12-03T10:15:30";
        argumentsManager.setDateFrom(from);

        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setDateFrom(from));
    }

    @ParameterizedTest(name = "The date {0} should be converted to {1}")
    @MethodSource("dateProvider")
    public void setArgumentTo_shouldParsStringToDate(String givenDate, LocalDateTime expectedLocalDateTime) {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setDateTo(givenDate);

        LocalDateTime actualLocalDateTime = argumentsManager.getDateTo();

        assertEquals(expectedLocalDateTime, actualLocalDateTime);

    }

    @Test()
    @DisplayName("setArgumentTo should throw error if already has this field")
    public void setArgumentTo_shouldThrowErrorIfDateAlreadyAdded() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String from = "2011-12-03T10:15:30";
        argumentsManager.setDateTo(from);

        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setDateTo(from));
    }

    @ParameterizedTest(name = "The input {0} should be converted to {1}")
    @MethodSource("formatProvider")
    public void setArgumentFormat_shouldParsFormatIfDataCorrect(String givenInput, OutputType expectedOutputType) {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setReportFormat(givenInput);

        OutputType actualLocalDateTime = argumentsManager.getReportFormat();

        assertEquals(expectedOutputType, actualLocalDateTime);

    }

    @Test()
    @DisplayName("setArgumentFormat should throw error if already has this field")
    public void setArgumentFormat_shouldThrowErrorIfDateAlreadyAdded() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String format = "adoc";
        argumentsManager.setReportFormat(format);

        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setReportFormat(format));
    }

    @Test()
    @DisplayName("setArgumentFormat should throw error if there is no format")
    public void setArgumentFormat_shouldThrowErrorIfThereIsNoFormat() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String format = "adocc";

        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setReportFormat(format));
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
        argumentsManager.setPath(path);

        assertThrows(IllegalArgumentException.class, () -> argumentsManager.setPath(path));
    }

    @Test()
    @DisplayName("setArgumentPath should add path if path not exist")
    public void setArgumentPath_shouldAddPathIfPathNotExist() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        String expectedPath = "a";
        argumentsManager.setPath(expectedPath);

        String actualPath = argumentsManager.getPath();

        assertEquals(expectedPath, actualPath);
    }

    @Test()
    @DisplayName("validate should work correctly if path exist")
    public void validate_shouldWorkCorrectlyIfThereIsPath() {
        ArgumentsManager argumentsManager = new ArgumentsManager();
        argumentsManager.setPath("a");

        argumentsManager.validate();
    }

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    public void parseArgs_shouldCorrectlyParseArguments(ArgumentsManager expectedArgumentsManager, String... args) {
        ArgumentsManager actualArgumentsManager = ArgumentsManager.parseArgs(args);

        assertEquals(expectedArgumentsManager, actualArgumentsManager);
    }

    @ParameterizedTest
    @MethodSource("wrongArgumentsProvider")
    public void parseArgs_shouldThrowErrorIfArgumentsIncorrect(String... args) {
        assertThrows(IllegalArgumentException.class, () -> {
            ArgumentsManager.parseArgs(args);
        });

    }
}
