package edu.hw3.task8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackwardIteratorTest {

    public static Stream<Arguments> hasNextProvider() {
        return Stream.of(
            Arguments.of(List.of(), false),
            Arguments.of(List.of(1), true)
        );
    }

    public static Stream<Arguments> nextProvider() {
        return Stream.of(
            Arguments.of(List.of(), List.of()),
            Arguments.of(List.of(1), List.of(1)),
            Arguments.of(List.of(1, 2, 3), List.of(3, 2, 1)),
            Arguments.of(List.of(1, 25, 32, -3, 76, 2), List.of(2, 76, -3, 32, 25, 1))
        );
    }

    @ParameterizedTest
    @MethodSource("hasNextProvider")
    public void hasNext_shouldWorkCorrectly(
        List list, boolean expectedAnswer
    ) {
        BackwardIterator backwardIterator = new BackwardIterator(list);

        boolean actualAnswer = backwardIterator.hasNext();

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest
    @MethodSource("nextProvider")
    public void add_shouldWorkCorrectly(
        List list, List expectedList
    ) {
        BackwardIterator backwardIterator = new BackwardIterator(list);

        List actualList = new ArrayList();

        while (backwardIterator.hasNext()) {
            actualList.add(backwardIterator.next());
        }

        assertEquals(expectedList, actualList);
    }

}
