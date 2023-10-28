package edu.hw3.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtbashEnglishTest {

    public static Stream<Arguments> codeMethodProvider() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of(
                "hello92-949jnsdnжоаовтдло ..2\\фююсьрвьыьтjnrnjc",
                "svool92-949qmhwmжоаовтдло ..2\\фююсьрвьыьтqmimqx"
            ),
            Arguments.of(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
            ),
            Arguments.of("", ""),
            Arguments.of("1", "1")
        );
    }

    @ParameterizedTest
    @MethodSource("codeMethodProvider")
    public void code_shouldCodeStringUsingAtbashCode(String inputString, String expectedString) {

        String actualString = new AtbashEnglish().code(inputString);

        assertEquals(expectedString, actualString);

    }

}
