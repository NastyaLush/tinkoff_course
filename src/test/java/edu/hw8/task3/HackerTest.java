package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
public class HackerTest {

    public static Stream<Arguments> hackProvider() {
        return Stream.of(
            Arguments.of(1, 10),
            Arguments.of(3, 10),
            Arguments.of(5, 10),
            Arguments.of(1, 100),
            Arguments.of(6, 100),
            Arguments.of(10, 100),
            Arguments.of(20, 100)
        );
    }

    @ParameterizedTest(name = "Index {index} count of threads: {0} count of data {1}")
    @MethodSource("hackProvider")
    public void checkPerformance(int countOfThreads, int countOfData)
        throws InterruptedException {
        Map<String, String> data = generateHashData(countOfData);
        var start = System.nanoTime();
        Hacker hacker = new Hacker(data);
        hacker.hack(countOfThreads);

        log.info("threads: " + countOfThreads + " data: " + countOfData + " time: " + (System.nanoTime() - start));
    }

    private static Map<String, String> generateHashData(int countOfPasswords) {
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < countOfPasswords; i++) {
            data.put(DigestUtils.md5Hex("us" + i), "user" + i);
        }
        return data;
    }

    private static Map<String, String> generateAnswerData(int countOfPasswords) {
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < countOfPasswords; i++) {
            data.put("user" + i, "us" + i);
        }
        return data;
    }

    @Test
    @DisplayName("the hack should correctly encrypt passwords")
    public void hack_shouldEncodePasswordsCorrectly() throws InterruptedException {
        Map<String, String> givenData = generateHashData(100);
        Map<String, String> expectedData = generateAnswerData(100);

        Hacker hacker = new Hacker(givenData);
        Map<String, String> actualData = hacker.hack(6);

        assertEquals(expectedData, actualData);

    }
}
