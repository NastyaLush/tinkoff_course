package edu.hw5.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class Friday13Test {

    private static final int MAX_YEAR_ALLOWED = 999999999;


    public static Stream<Arguments> friday13ByYearsProvider() {
        return Stream.of(
                Arguments.of(1925, List.of(LocalDate.of(1925, 2, 13), LocalDate.of(1925, 3, 13), LocalDate.of(1925, 11, 13))),
                Arguments.of(2024, List.of(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13))),
                Arguments.of(0, List.of(LocalDate.of(0, 10, 13))),
                Arguments.of(MAX_YEAR_ALLOWED, List.of(LocalDate.of(999999999, 8, 13))),
                Arguments.of(-MAX_YEAR_ALLOWED, List.of(LocalDate.of(-999999999, 4, 13), LocalDate.of(-999999999, 7, 13)))
        );
    }

    public static Stream<Arguments> nextFriday13Provider() {
        return Stream.of(
                Arguments.of(LocalDate.of(1925, 1, 1), LocalDate.of(1925, 2, 13)),
                Arguments.of(LocalDate.of(1925, 12, 1), LocalDate.of(1926, 8, 13)),
                Arguments.of(LocalDate.of(1925, 3, 13), LocalDate.of(1925, 11, 13)),
                Arguments.of(LocalDate.of(MAX_YEAR_ALLOWED, 12, 30), null)
        );
    }

    public static Stream<Arguments> wrongYearsProvider() {
        return Stream.of(
                Arguments.of(MAX_YEAR_ALLOWED + 1),
                Arguments.of(-MAX_YEAR_ALLOWED - 1)
        );
    }

    @ParameterizedTest
    @MethodSource("friday13ByYearsProvider")
    public void getFridays13OfGivenYear_shouldReturnAllFridays15OfTheYear(Integer year, List<LocalDate> expectedFridays) {
        Friday13 friday13 = new Friday13();

        List<LocalDate> actualFridays = friday13.getFridays13OfGivenYear(year);

        assertEquals(expectedFridays, actualFridays);
    }

    @ParameterizedTest
    @MethodSource("wrongYearsProvider")
    public void getFridays13OfGivenYear_shouldThrowErrorIfDataIncorrect(Integer year) {
        Friday13 friday13 = new Friday13();

        assertThrows(IllegalArgumentException.class, () -> friday13.getFridays13OfGivenYear(year));

    }

    @ParameterizedTest
    @MethodSource("nextFriday13Provider")
    public void getNextFriday13_shouldReturnNextFriday13(LocalDate current, LocalDate expectedFriday13) {
        Friday13 friday13 = new Friday13();

        LocalDate actualFriday13 = friday13.getNextFriday13(current);

        assertEquals(expectedFriday13, actualFriday13);

    }


}
