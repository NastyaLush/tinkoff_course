package edu.hw8.task3;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Log4j2
public class HackerTest {

    public static Stream<Arguments> hackProvider() {
        return Stream.of(
            Arguments.of(1, 10),
            Arguments.of(3, 10),
            Arguments.of(5, 10),
            Arguments.of(1, 100),
            Arguments.of(5, 100),
            Arguments.of(10, 100),
            Arguments.of(20, 100)
        );
    }

    private static Map<String, String> generateData(int countOfPasswords) {
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < countOfPasswords; i++) {
            data.put(DigestUtils.md5Hex("us" + i), "user" + i);
        }
        return data;
    }

    @ParameterizedTest(name = "Index {index} count of threads: {0} count of data {1}")
    @MethodSource("hackProvider")
    public void test(int countOfThreads, int countOfData)
        throws NoSuchAlgorithmException, InterruptedException {
        Map<String, String> data = generateData(countOfData);
        var start = System.nanoTime();
        Hacker hacker = new Hacker(data);
        hacker.hack(countOfThreads);

        log.info("threads: " + countOfThreads + " data: " + countOfData + " time: " + (System.nanoTime() - start));
    }
}
