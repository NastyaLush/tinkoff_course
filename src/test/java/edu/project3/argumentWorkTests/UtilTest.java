package edu.project3.argumentWorkTests;

import edu.project3.argumentWork.Util;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

public class UtilTest {

    public static Stream<Arguments> pathCorrectProvider() {
        return Stream.of(
                Arguments.of("https://edu.tinkoff.ru/my-activities/"),
                Arguments.of("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"),
                Arguments.of("logs/log1.txt"),
                Arguments.of("logs/dir/"),
                Arguments.of("logs/log*"),
                Arguments.of("logs/**/log.txt"),
                Arguments.of("logs/**/logo.txt")
        );
    }

    public static Stream<Arguments> pathIncorrectConvertProvider() {
        return Stream.of(
                Arguments.of("logs*/dir"),
                Arguments.of("logs*/log*.txt"),
                Arguments.of("logs/log**"),
                Arguments.of("logs/*/log.txt"),
                Arguments.of("logs/log1.tx"),
                Arguments.of("log/dir/")
        );
    }

    public static Stream<Arguments> streamProvider() throws IOException, InterruptedException {
        return Stream.of(
                Arguments.of("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                        Util.getStream("logs/site_log/log1.txt")
                ),
                Arguments.of("logs/log1.txt", Stream.of("93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"", "93.180.71.3 - - [17/May/2015:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"")),
                Arguments.of("logs", Stream.concat(Stream.concat(Util.getStream("logs/log1.txt")
                        , Util.getStream("logs/log.txt")), Util.getStream("logs/ilog3.txt"))),
                Arguments.of("logs/log*", Stream.concat(Util.getStream("logs/log1.txt")
                        , Util.getStream("logs/log.txt"))),
                Arguments.of("logs/logi*", Stream.of()),
                Arguments.of("logs/**/log.txt", Stream.concat(Util.getStream("logs/log.txt")
                        , Util.getStream("logs/dir/log.txt"))),
                Arguments.of("logs/**/logo.txt", Stream.of())
        );
    }

    @ParameterizedTest(name = "path {0}")
    @MethodSource("pathCorrectProvider")
    public void getStream_shouldReturnStreamStringIfPathIsCorrect(String givenPath) throws Exception {
        Util.getStream(givenPath);
    }

    @ParameterizedTest(name = "path {0}")
    @MethodSource("pathIncorrectConvertProvider")
    public void getStream_shouldThrowErrorIfImpossibleConvertPath(String givenPath) {
        assertThrows(IllegalArgumentException.class, () -> {
            Util.getStream(givenPath);
        });
    }

    @ParameterizedTest(name = "path {0}")
    @MethodSource("streamProvider")
    public void getStream_shouldReturnCorrectStream(String givenPath, Stream<String> expectedStream) throws Exception {
        Stream<String> actualStream = Util.getStream(givenPath);
        assertStreamEquals(expectedStream, actualStream);
    }

    static void assertStreamEquals(Stream<String> expectedStream, Stream<String> actualStream) {
        Iterator<String> expectedIter = expectedStream.sorted()
                                                      .iterator(), actualIter = actualStream.sorted()
                                                                                            .iterator();
        while (expectedIter.hasNext() && actualIter.hasNext())
            assertEquals(expectedIter.next(), actualIter.next());
        assert !expectedIter.hasNext() && !actualIter.hasNext();
    }


}
