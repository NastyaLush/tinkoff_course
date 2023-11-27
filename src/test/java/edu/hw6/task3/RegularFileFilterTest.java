package edu.hw6.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RegularFileFilterTest {

    public static Stream<Arguments> regularPathProvider() {
        return Stream.of(
                Arguments.of(Path.of("test"), Pattern.compile("t\\w*t"), true),
                Arguments.of(Path.of("test.png"), Pattern.compile("t\\w*t"), false),
                Arguments.of(Path.of("test.png"), Pattern.compile("t.*t.*"), true),
                Arguments.of(Path.of("test.png"), Pattern.compile("\\w*.png"), true)
        );
    }

    @ParameterizedTest(name = "Path {0}, pattern {1} expected answer {2}")
    @MethodSource("regularPathProvider")
    public void accept_shouldWorkCorrectly(Path path, Pattern pattern, boolean expectedResult) throws IOException {
        RegularPatternFilter regularPatternFilter = new RegularPatternFilter(pattern);

        boolean actualResult = regularPatternFilter.accept(path);

        assertEquals(expectedResult, actualResult);

    }

}
