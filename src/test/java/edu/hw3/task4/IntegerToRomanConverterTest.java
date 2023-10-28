package edu.hw3.task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegerToRomanConverterTest {

    public static Stream<Arguments> correctInputConvertToRomanProvider() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(4, "IV"),
            Arguments.of(5, "V"),
            Arguments.of(7, "VII"),
            Arguments.of(9, "IX"),
            Arguments.of(10, "X"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(49, "XLIX"),
            Arguments.of(86, "LXXXVI"),
            Arguments.of(98, "XCVIII"),
            Arguments.of(100, "C"),
            Arguments.of(234, "CCXXXIV"),
            Arguments.of(756, "DCCLVI"),
            Arguments.of(999, "CMXCIX"),
            Arguments.of(1000, "M"),
            Arguments.of(2465, "MMCDLXV"),
            Arguments.of(4999, "MMMMCMXCIX")

        );
    }

    public static Stream<Arguments> incorrectInputConvertToRomanProvider() {
        return Stream.of(
            Arguments.of(0),
            Arguments.of(-1),
            Arguments.of(5000),
            Arguments.of(5001)

        );
    }

    @ParameterizedTest
    @MethodSource("correctInputConvertToRomanProvider")
    public void convertToRoman_shouldReturnRomanIfRomanBetween1And5000(Integer givenNumber, String expectedRoman) {

        String actualRoman = new IntegerToRomanConverter().convertToRoman(givenNumber);

        assertEquals(expectedRoman, actualRoman);

    }

    @ParameterizedTest
    @MethodSource("incorrectInputConvertToRomanProvider")
    public void convertToRoman_shouldThrowExceptionIfNumberNotBetween1And5000(
        Integer givenNumber
    ) {

        assertThrows(IllegalArgumentException.class, () -> new IntegerToRomanConverter().convertToRoman(givenNumber));

    }

}
