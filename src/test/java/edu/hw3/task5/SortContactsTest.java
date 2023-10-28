package edu.hw3.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortContactsTest {

    public static Stream<Arguments> parseContactsProvider() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"},
                SortOrder.ASC
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"},
                SortOrder.DESC
            ),
            Arguments.of(
                new String[] {},
                new String[] {},
                SortOrder.DESC
            ),
            Arguments.of(
                null,
                new String[] {},
                SortOrder.DESC
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Euler", "Carl Gauss"},
                new String[] {"Carl Gauss", "Euler", "Paul Erdos"},
                SortOrder.DESC
            ),
            Arguments.of(
                new String[] {"Erdos", "Euler", "Gauss"},
                new String[] {"Erdos", "Euler", "Gauss"},
                SortOrder.ASC
            )
        );
    }

    public static Stream<Arguments> parseContactsErrorProvider() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes Hasman"},
                new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"},
                SortOrder.ASC
            ));
    }

    @ParameterizedTest
    @MethodSource("parseContactsProvider")
    public void parseContacts_shouldSortContacts(
        String[] inputContacts,
        String[] expectedContacts,
        SortOrder sortOrder
    ) {

        String[] actualContacts = new SortContacts().parseContacts(inputContacts, sortOrder);

        assertTrue(Arrays.equals(expectedContacts, actualContacts));

    }

    @ParameterizedTest
    @MethodSource("parseContactsErrorProvider")
    public void parseContacts_shouldThrowErrorIfInoutIncorrect(
        String[] inputContacts,
        String[] expectedContacts,
        SortOrder sortOrder
    ) {
        assertThrows(IllegalArgumentException.class, () -> new SortContacts().parseContacts(inputContacts, sortOrder));
    }

}
