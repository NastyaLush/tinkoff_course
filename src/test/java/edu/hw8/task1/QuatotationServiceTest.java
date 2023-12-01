package edu.hw8.task1;

import edu.hw8.task1.server.QuatotationService;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuatotationServiceTest {

    private static final String quote = "I will not apologize for simply expediting the inevitable";
    private static final String quoteNoAnswer = "There is no quote with this word";

    @Test
    @DisplayName("add quote should add quotes to the service")
    public void addQuote_shouldWorkCorrectly() {
        QuatotationService quatotationService = new QuatotationService();
        List<String> givenQuotes = List.of(
            "sss",
            "sss",
            "sss",
            "sss",
            "sss",
            "sss",
            "sss",
            "sss",
            "sss"
        );
        for (String quote : givenQuotes) {
            quatotationService.addQuote(quote);
        }
        List<String> actualQoutes = quatotationService.getQuotes();
        assertArrayEquals(givenQuotes.toArray(), actualQoutes.toArray());
    }

    private static QuatotationService generateQuatotationService() {
        QuatotationService quatotationService = new QuatotationService();
        quatotationService.addQuote(quote);
        return quatotationService;
    }

    public static Stream<Arguments> quoteProvider() {
        return Stream.of(
            Arguments.of(generateQuatotationService(), "will", quote),
            Arguments.of(generateQuatotationService(), "I", quote),
            Arguments.of(generateQuatotationService(), "not", quote),
            Arguments.of(generateQuatotationService(), "apologize", quote),
            Arguments.of(generateQuatotationService(), "inevitable", quote),
            Arguments.of(generateQuatotationService(), "wil", quoteNoAnswer),
            Arguments.of(generateQuatotationService(), "i", quoteNoAnswer),
            Arguments.of(generateQuatotationService(), "", quoteNoAnswer),
            Arguments.of(generateQuatotationService(), null, quoteNoAnswer)
        );
    }

    @ParameterizedTest(name = "Index {index} word: {1} answer: {2}")
    @MethodSource("quoteProvider")
    public void getQuote_shouldReturnQuoteIfItContainsThisWord(
        QuatotationService quatotationService,
        String givenWord,
        String expectedAnswer
    ) {

        String actualQuote = quatotationService.getQuote(givenWord);
        assertEquals(expectedAnswer, actualQuote);
    }
}
