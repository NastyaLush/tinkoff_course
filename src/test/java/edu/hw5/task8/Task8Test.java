package edu.hw5.task8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class Task8Test {

    public static Stream<Arguments> isOddStringProvider() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("0", true),
                Arguments.of("00", false),
                Arguments.of("000", true),
                Arguments.of("1", true),
                Arguments.of("10", false),
                Arguments.of("110", true)
        );
    }

    public static Stream<Arguments> isBeginsWith0AndOddOrBeginsWith1AndEvenProvider() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("0", true),
                Arguments.of("01", false),
                Arguments.of("00", false),
                Arguments.of("000", true),
                Arguments.of("011", true),
                Arguments.of("1", false),
                Arguments.of("10", true),
                Arguments.of("110", false)
        );
    }

    public static Stream<Arguments> isCountOfZerousMultiple3Provider() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("0", false),
                Arguments.of("01", false),
                Arguments.of("101", false),
                Arguments.of("00", false),
                Arguments.of("000", true),
                Arguments.of("101010", true),
                Arguments.of("010101", true),
                Arguments.of("01010", true),
                Arguments.of("01000010", true),
                Arguments.of("000000", true),
                Arguments.of("000111", true),
                Arguments.of("111000", true),
                Arguments.of("101100", true),
                Arguments.of("0000", false),
                Arguments.of("0101", false),
                Arguments.of("0110", false)
        );
    }

    public static Stream<Arguments> isNot11or111Provider() {
        return Stream.of(
                Arguments.of("", true),
                Arguments.of("11", false),
                Arguments.of("111", false),
                Arguments.of("0", true),
                Arguments.of("01", true),
                Arguments.of("101", true),
                Arguments.of("00", true),
                Arguments.of("000", true),
                Arguments.of("1111", true),
                Arguments.of("1", true),
                Arguments.of("011", true),
                Arguments.of("0111", true),
                Arguments.of("1110", true),
                Arguments.of("110", true),
                Arguments.of("0110", true),
                Arguments.of("01110", true)
        );
    }

    public static Stream<Arguments> isOddDigitIs1Provider() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("0", false),
                Arguments.of("1", true),
                Arguments.of("10", true),
                Arguments.of("11", true),
                Arguments.of("110", false),
                Arguments.of("101", true),
                Arguments.of("01", false),
                Arguments.of("00", false),
                Arguments.of("1010100", false),
                Arguments.of("1010101", true)
        );
    }

    public static Stream<Arguments> moreOrEqual2ZerousAndOneOrLessOneProvider() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("0", false),
                Arguments.of("1", false),
                Arguments.of("10", false),
                Arguments.of("11", false),
                Arguments.of("1100", false),
                Arguments.of("100", true),
                Arguments.of("010", false),
                Arguments.of("001", true),
                Arguments.of("100", true),
                Arguments.of("0010", true),
                Arguments.of("0100", true),
                Arguments.of("0110", false),
                Arguments.of("1010", false)
        );
    }

    public static Stream<Arguments> noConsecutive1Provider() {
        return Stream.of(
                Arguments.of("", true),
                Arguments.of("0", true),
                Arguments.of("1", true),
                Arguments.of("10", true),
                Arguments.of("11", false),
                Arguments.of("1100", false),
                Arguments.of("001100", false),
                Arguments.of("0011", false),
                Arguments.of("100", true),
                Arguments.of("010", true),
                Arguments.of("001", true),
                Arguments.of("100", true),
                Arguments.of("0010", true),
                Arguments.of("0100", true),
                Arguments.of("0110", false),
                Arguments.of("1010", true),
                Arguments.of("0101", true),
                Arguments.of("010000100010100", true)
        );
    }

    @ParameterizedTest(name = "Check if {0} is odd")
    @MethodSource("isOddStringProvider")
    public void isOddString_shouldReturnTrueIfStringIsOdd(String givenString, boolean expectedAnswer) {
        Task8 task8 = new Task8();

        boolean actualAnswer = task8.isOddString(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "Check if string {0} begins with 0 and odd or begins with 1 and even")
    @MethodSource("isBeginsWith0AndOddOrBeginsWith1AndEvenProvider")
    public void isBeginsWith0AndOddOrBeginsWith1AndEven_shouldReturnTrueIfStringBeginsWith0AndOddOrBeginsWith1AndEven(String givenString, boolean expectedAnswer) {
        Task8 task8 = new Task8();

        boolean actualAnswer = task8.isBeginsWith0AndOddOrBeginsWith1AndEven(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "Check if string \"{0}\" has 0 multiple 3")
    @MethodSource("isCountOfZerousMultiple3Provider")
    public void isCountOfZerousMultiple3_shouldReturnTrueIfCountOfZerousMultiple3(String givenString, boolean expectedAnswer) {
        Task8 task8 = new Task8();

        boolean actualAnswer = task8.isCountOfZerousMultiple3(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "Check if string \"{0}\" begins with 0 and odd or begins with 1 and even")
    @MethodSource("isNot11or111Provider")
    public void isNot11or111_shouldReturnTrueIfStringNotEqual11And111(String givenString, boolean expectedAnswer) {
        Task8 task8 = new Task8();

        boolean actualAnswer = task8.isNot11or111(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "Check if string {0} has every odd equal 1")
    @MethodSource("isOddDigitIs1Provider")
    public void isOddDigitIs1_shouldReturnTrueIfEveryOddIs1(String givenString, boolean expectedAnswer) {
        Task8 task8 = new Task8();

        boolean actualAnswer = task8.isOddDigitIs1(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "Check if string {0} has every odd number equal 1")
    @MethodSource("moreOrEqual2ZerousAndOneOrLessOneProvider")
    public void moreOrEqual2ZerousAndOneOrLessOne_shouldReturnTrueIf0MoreOrEqual2And1LessOrEqual1(String givenString, boolean expectedAnswer) {
        Task8 task8 = new Task8();

        boolean actualAnswer = task8.moreOrEqual2ZerousAndOneOrLessOne(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @ParameterizedTest(name = "Check if string {0} has no consecutive 1")
    @MethodSource("noConsecutive1Provider")
    public void noConsecutive1_shouldReturnTrueIfNoConsecutive1(String givenString, boolean expectedAnswer) {
        Task8 task8 = new Task8();

        boolean actualAnswer = task8.noConsecutive1(givenString);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
