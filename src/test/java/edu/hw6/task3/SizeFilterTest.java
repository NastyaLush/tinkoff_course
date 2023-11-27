package edu.hw6.task3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class SizeFilterTest {

    private static final Path path = Path.of("test");

    public static Stream<Arguments> sizeProvider() throws IOException {
        return Stream.of(
                Arguments.of(Files.size(path) - 1, SizeFilterOptions.SMALLER, false),
                Arguments.of(Files.size(path), SizeFilterOptions.SMALLER, false),
                Arguments.of(Files.size(path) + 1, SizeFilterOptions.SMALLER, true),
                Arguments.of(Files.size(path) - 1, SizeFilterOptions.LARGER, true),
                Arguments.of(Files.size(path), SizeFilterOptions.LARGER, false),
                Arguments.of(Files.size(path) + 1, SizeFilterOptions.LARGER, false),
                Arguments.of(Files.size(path) - 1, SizeFilterOptions.EQUAL, false),
                Arguments.of(Files.size(path), SizeFilterOptions.EQUAL, true)
        );
    }

    @BeforeAll
    private static void createFile() throws IOException {
        Files.createFile(path);
        Files.write(path, "test string".getBytes());
    }

    @ParameterizedTest(name = "size:{0} option: {1}, expected answer {2}")
    @MethodSource("sizeProvider")
    public void accept_shouldWorkCorrectly(Long size, SizeFilterOptions option, boolean expectedAnswer) throws IOException {
        SizeFilter sizeFilter = new SizeFilter(size, option);

        Boolean actualAnswer = sizeFilter.accept(path);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @AfterAll
    private static void deleteFile() throws IOException {
        Files.deleteIfExists(path);
    }

}
