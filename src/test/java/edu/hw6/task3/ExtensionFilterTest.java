package edu.hw6.task3;

import edu.hw6.task3.ExtensionFilter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ExtensionFilterTest {

    public static Stream<Arguments> extensionProvider() {
        return Stream.of(
                Arguments.of(Path.of("test.ass.as"), ".as", true),
                Arguments.of(Path.of("test.as"), ".as", true),
                Arguments.of(Path.of("test.ass.ass"), ".as", false),
                Arguments.of(Path.of("test"), ".as", false),
                Arguments.of(Path.of("test"), "", true)
        );
    }


    @ParameterizedTest(name = "Given path {0}, extension {1}, expected answer {2}")
    @MethodSource("extensionProvider")
    public void accept_shouldWorkCorrectly(Path givenPath, String extension, Boolean expectedAnswer) throws IOException {
        ExtensionFilter extensionFilter = new ExtensionFilter(extension);

        Boolean actualAnswer = extensionFilter.accept(givenPath);
        assertEquals(expectedAnswer, actualAnswer);
    }

}
