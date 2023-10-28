package edu.hw3.task3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrequencyDictionaryTest {

    public static Stream<Arguments> freqDictProvider() {
        return Stream.of(
            Arguments.of(new ArrayList<>(List.of("a", "bb", "a", "bb")), new HashMap<String, Integer>() {{
                put("bb", 2);
                put("a", 2);
            }}),
            Arguments.of(new ArrayList<>(List.of("this", "and", "that", "and")), new HashMap<String, Integer>() {{
                put("that", 1);
                put("and", 2);
                put("this", 1);
            }}),
            Arguments.of(new ArrayList<>(List.of("код", "код", "код", "bug")), new HashMap<String, Integer>() {{
                put("код", 3);
                put("bug", 1);
            }}),
            Arguments.of(new ArrayList<>(List.of(1, 1, 2, 3, 2)), new HashMap<Integer, Integer>() {{
                put(1, 2);
                put(2, 2);
                put(3, 1);
            }}),
            Arguments.of(new ArrayList<>(List.of(
                IllegalArgumentException.class,
                NullPointerException.class,
                IllegalArgumentException.class,
                IllegalArgumentException.class
            )), new HashMap<>() {{
                put(IllegalArgumentException.class, 3);
                put(NullPointerException.class, 1);
            }}),
            Arguments.of(new ArrayList<>(List.of(
                new TestClass("name", 2),
                new TestClass("name", 2),
                new TestClass("name", 2),
                new TestClass("name2", 2),
                new TestClass("name3", 2),
                new TestClass("name", 4),
                new TestClass("name", 4)
            )), new HashMap<TestClass, Integer>() {{
                put(new TestClass("name", 2), 3);
                put(new TestClass("name2", 2), 1);
                put(new TestClass("name3", 2), 1);
                put(new TestClass("name", 4), 2);
            }})
        );
    }

    @ParameterizedTest
    @MethodSource("freqDictProvider")
    public <T> void freqDict_shouldReturnFrequencyDictionary(
        ArrayList<T> dictionary, HashMap<T, Integer> expectedFreqDict
    ) {

        HashMap<T, Integer> actualFreqDict = new FrequencyDictionary().freqDict(dictionary);

        assertEquals(expectedFreqDict, actualFreqDict);
    }

}
