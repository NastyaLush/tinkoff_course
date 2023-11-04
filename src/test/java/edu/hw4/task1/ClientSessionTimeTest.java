package edu.hw4.task1;

import edu.hw5.task1.ClientSessionTime;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

public class ClientSessionTimeTest {


    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public static Stream<Arguments> clientSessionTimeProvider() {
        return Stream.of(
                Arguments.of("2022-03-12, 20:20 - 2022-03-12, 23:50\n2022-04-01, 21:30 - 2022-04-02, 01:20", "3ч 40м"),
                Arguments.of("2022-03-12, 20:20 - 2022-03-13, 23:50\n2022-04-01, 21:30 - 2022-04-03, 01:20", "27ч 40м"),
                Arguments.of("2022-03-12, 20:20 - 2023-03-12, 23:50\n2022-04-01, 21:30 - 2023-04-02, 01:20", "8763ч 40м"),
                Arguments.of("2022-03-12, 20:20 - 2022-03-12, 23:50\n2022-04-01, 21:30 - 2022-04-01, 21:30", "1ч 45м"),
                Arguments.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "3ч 30м"),
                Arguments.of("", "0ч 0м")
        );
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @ParameterizedTest
    @MethodSource("clientSessionTimeProvider")
    public void printDuration_shouldPrintAvgSessionDuration(String input, String expectedOutput) {
        ClientSessionTime clientSessionTime = new ClientSessionTime();

        clientSessionTime.printDuration(input);

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
