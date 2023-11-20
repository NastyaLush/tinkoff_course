package edu.hw5.task5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class CarNumberDateParserTest {

    public static Stream<Arguments> carNumberProvider() {
        return Stream.of(
            Arguments.of("A123BE777", true),
            Arguments.of("O777OO177", true),
            Arguments.of("A123BE77", true),
            Arguments.of("A123BE7777", false),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123LЕ777", false),
            Arguments.of("L123BЕ777", false),
            Arguments.of("А12BЕ777", false),
            Arguments.of("А122BЕ7", false),
            Arguments.of("LА122BЕ77", false)

        );
    }

    @ParameterizedTest(name = "is valid car should check if {0} valid")
    @MethodSource("carNumberProvider")
    public void isValidCarNumber_shouldWorkCorrectly(String givenCarNumber, boolean expectedAnswer) {

        boolean actualAnswer = new CarNumberValidator().isValidCarNumber(givenCarNumber);

        assertEquals(expectedAnswer, actualAnswer);
    }

}
